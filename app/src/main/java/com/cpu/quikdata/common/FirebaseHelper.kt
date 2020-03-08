package com.cpu.quikdata.common

import android.net.Uri
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.cpu.quikdata.*
import com.cpu.quikdata.data.AppDatabase
import com.cpu.quikdata.data.casestories.casestoriesimage.CaseStoriesImageItem
import com.cpu.quikdata.data.form.Form
import com.cpu.quikdata.data.form.FormStatus
import com.google.android.gms.tasks.Task
import com.google.android.gms.tasks.Tasks
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.WriteBatch
import com.google.firebase.storage.FirebaseStorage
import java.util.*
import com.google.firebase.storage.UploadTask
import java.util.concurrent.TimeUnit
import java.util.concurrent.TimeoutException
import kotlin.collections.ArrayList

enum class ProgressNotification {
    FORM_SUBMITTED,
    IMAGE_UPLOADED,
    IMAGE_UPLOAD_COMPLETED,
    FINISHED,
    ERROR_OCCURRED,
    CANCELLED
}

class FirebaseHelper(
    private val mFirestore: FirebaseFirestore,
    private val mStorage: FirebaseStorage,
    private val mDatabase: AppDatabase
) {

    private val mUploadTasks = arrayListOf<UploadTask>()

    @Volatile
    private var mIsCancelled = false

    fun cancelSubmission() {
        mIsCancelled = true

        // TODO: Remove this afterwards
        // System.out.println("============ CANCELLING SUBMISSION =============")

        cancelUploadTasks()
    }

    private fun cancelUploadTasks() {
        mUploadTasks.forEach {
            it.cancel()
        }
        mUploadTasks.clear()
    }

    private fun getOnProgressListener(resultLiveData: MutableLiveData<ProgressNotification>) =
        { pn: ProgressNotification ->
            if (mIsCancelled) {
                resultLiveData.postValue(ProgressNotification.CANCELLED)
            } else {
                // System.out.println("============ $pn =============")
                when (pn) {
                    ProgressNotification.FINISHED,
                    ProgressNotification.CANCELLED,
                    ProgressNotification.ERROR_OCCURRED -> {
                        if (resultLiveData.value != pn) {
                            resultLiveData.postValue(pn)
                        }
                    }
                    else -> {
                        resultLiveData.postValue(pn)
                    }
                }
            }
        }

    // region Submission methods

    suspend fun sendBasicData(formId: String): List<UploadTask> {
        mFirestore.batch().apply {

            // Retrieve form and update its status in DB
            val form = retrieveForm(formId)
            form.formStatus = FormStatus.SUBMITTING
            mDatabase.formDao().update(form)

            // Submit actual form data
            submitFormDetails(mDatabase, formId, this)
            submitGeneralInformation(mDatabase, formId, this)

            val images = sendCaseStories(mDatabase, formId, this)
            commit()

            val uploadImageTasks = ArrayList<UploadTask>()
            while (images.isNotEmpty()) {
                val uploadTask = createUploadImageTask(images.remove())
                uploadImageTasks.add(uploadTask)
                uploadTask.addOnCompleteListener {
                    uploadImageTasks.remove(uploadTask)
                }
            }
            return uploadImageTasks
        }
    }

    suspend fun sendAllData(formId: String): List<UploadTask> {
        mFirestore.batch().apply {

            // Retrieve form and update its status in DB
            val form = retrieveForm(formId)
            form.formStatus = FormStatus.SUBMITTING
            mDatabase.formDao().update(form)

            // Submit actual form data
            submitFormDetails(mDatabase, formId, this)
            submitGeneralInformation(mDatabase, formId, this)
            if (form.includeShelter) { submitShelterInformation(mDatabase, formId, this) }
            if (form.includeFood) { submitFoodSecurity(mDatabase, formId, this) }
            if (form.includeLivelihoods) { submitLivelihoods(mDatabase, formId, this) }
            if (form.includeHealth) { submitHealthInformation(mDatabase, formId, this) }
            if (form.includeWash) { submitWashInformation(mDatabase, formId, this) }
            if (form.includeEvacuation) { submitEvacuationInformation(mDatabase, formId, this) }

            val images = sendCaseStories(mDatabase, formId, this)
            commit()

            val uploadImageTasks = ArrayList<UploadTask>()
            while (images.isNotEmpty()) {
                val uploadTask = createUploadImageTask(images.remove())
                uploadImageTasks.add(uploadTask)
                uploadTask.addOnCompleteListener {
                    uploadImageTasks.remove(uploadTask)
                }
            }
            return uploadImageTasks
        }
    }

    private suspend fun retrieveForm(formId: String): Form {
        return mDatabase.formDao().getByIdNonLive(formId)
    }

    private suspend fun createUploadImageTask(image: CaseStoriesImageItem): UploadTask {
        return mStorage.reference.child("images/${image.id}")
            .putFile(Uri.parse(image.uri))
    }

    suspend fun submitBasicData(formId: String): LiveData<ProgressNotification> {
        cancelUploadTasks()
        mIsCancelled = false
        val resultLiveData = MutableLiveData<ProgressNotification>()
        val progressListener = getOnProgressListener(resultLiveData)

        runWithSuccessCounter(2, progressListener, ProgressNotification.FINISHED) { pnl ->
            val batch = mFirestore.batch()
            submitFormDetails(mDatabase, formId, batch)
            submitGeneralInformation(mDatabase, formId, batch)
            submitCaseStories(mDatabase, formId, batch, progressListener, pnl)
            batch.commit()
                .addOnFailureListener { progressListener(ProgressNotification.ERROR_OCCURRED) }
                .addOnSuccessListener {
                    progressListener(ProgressNotification.FORM_SUBMITTED)
                    pnl()
                }
        }

        return resultLiveData
    }

    suspend fun submitAllData(formId: String): LiveData<ProgressNotification> {
        cancelUploadTasks()
        mIsCancelled = false
        val resultLiveData = MutableLiveData<ProgressNotification>()
        val progressListener = getOnProgressListener(resultLiveData)

        val formData = mDatabase.formDao().getFormDataNonLive(formId)
        runWithSuccessCounter(2, progressListener, ProgressNotification.FINISHED) { pnl ->
            val batch = mFirestore.batch()
            submitFormDetails(mDatabase, formId, batch)
            submitGeneralInformation(mDatabase, formId, batch)
            submitCaseStories(mDatabase, formId, batch, progressListener, pnl)
            if (formData.form!!.includeShelter) submitShelterInformation(mDatabase, formId, batch)
            if (formData.form!!.includeFood) submitFoodSecurity(mDatabase, formId, batch)
            if (formData.form!!.includeLivelihoods) submitLivelihoods(mDatabase, formId, batch)
            if (formData.form!!.includeHealth) submitHealthInformation(mDatabase, formId, batch)
            if (formData.form!!.includeWash) submitWashInformation(mDatabase, formId, batch)
            if (formData.form!!.includeEvacuation) submitEvacuationInformation(
                mDatabase,
                formId,
                batch
            )
            batch.commit()
                .addOnFailureListener { progressListener(ProgressNotification.ERROR_OCCURRED) }
                .addOnSuccessListener {
                    progressListener(ProgressNotification.FORM_SUBMITTED)
                    pnl()
                }
        }

        return resultLiveData
    }

    private suspend fun submitFormDetails(
        database: AppDatabase,
        formId: String,
        batch: WriteBatch
    ) {
        run {
            val section = database.formDao().getFormDataNonLive(formId)
            batch.setTask(
                mFirestore.collection(FIREBASE_KEY_FORM).document(section.form!!.id),
                section
            )
        }
    }

    private suspend fun submitGeneralInformation(
        database: AppDatabase,
        formId: String,
        batch: WriteBatch
    ) {
        run {
            val section = database.calamityInfoDao().getByFormIdNonLive(formId)
            batch.setTask(
                mFirestore.collection(FIREBASE_KEY_CALAMITY_INFO).document(section.id),
                section
            )
        }
        run {
            val section = database.populationRowDao().getByFormIdNonLive(formId)
            batch.setTask(
                mFirestore.collection(FIREBASE_KEY_POPULATION).document(formId),
                ListWrapper(formId, section)
            )
        }
        run {
            val section = database.familiesDao().getByFormIdNonLive(formId)
            batch.setTask(
                mFirestore.collection(FIREBASE_KEY_FAMILIES).document(section.id),
                section
            )
        }
        run {
            val section = database.vulnerableRowDao().getByFormIdNonLive(formId)
            batch.setTask(
                mFirestore.collection(FIREBASE_KEY_VULNERABLE).document(formId),
                ListWrapper(formId, section)
            )
        }
        run {
            val section = database.casualtiesRowDao().getByFormIdNonLive(formId)
            batch.setTask(
                mFirestore.collection(FIREBASE_KEY_CASUALTIES).document(formId),
                ListWrapper(formId, section)
            )
        }
        run {
            val section = database.causeOfDeathRowDao().getByFormIdNonLive(formId)
            batch.setTask(
                mFirestore.collection(FIREBASE_KEY_CAUSE_OF_DEATH).document(formId),
                ListWrapper(formId, section)
            )
        }
        run {
            val section = database.infrastructureDamageRowDao().getByFormIdNonLive(formId)
            batch.setTask(
                mFirestore.collection(FIREBASE_KEY_INFRASTRUCTURE).document(formId),
                ListWrapper(formId, section)
            )
        }
    }

    private suspend fun submitShelterInformation(
        database: AppDatabase,
        formId: String,
        batch: WriteBatch
    ) {
        run {
            val section = database.houseDamageRowDao().getByFormIdNonLive(formId)
            batch.setTask(
                mFirestore.collection(FIREBASE_KEY_HOUSE_DAMAGE).document(formId),
                ListWrapper(formId, section)
            )
        }
        run {
            val section = database.shelterCopingDao().getByFormIdNonLive(formId)
            batch.setTask(
                mFirestore.collection(FIREBASE_KEY_SHELTER_COPING).document(section.id),
                section
            )
        }
        run {
            val section = database.shelterNeedsRowDao().getByFormIdNonLive(formId)
            batch.setTask(
                mFirestore.collection(FIREBASE_KEY_SHELTER_NEEDS).document(formId),
                ListWrapper(formId, section)
            )
        }
        run {
            val section = database.shelterAssistanceRowDao().getByFormIdNonLive(formId)
            batch.setTask(
                mFirestore.collection(FIREBASE_KEY_SHELTER_ASSISTANCE).document(formId),
                ListWrapper(formId, section)
            )
        }
        run {
            val section = database.shelterGapsDao().getByFormIdNonLive(formId)
            batch.setTask(
                mFirestore.collection(FIREBASE_KEY_SHELTER_GAPS).document(section.id),
                section
            )
        }
    }

    private suspend fun submitFoodSecurity(
        database: AppDatabase,
        formId: String,
        batch: WriteBatch
    ) {
        run {
            val section = database.foodSecurityImpactDao().getByFormIdNonLive(formId)
            batch.setTask(
                mFirestore.collection(FIREBASE_KEY_FOOD_IMPACT).document(section.id),
                section
            )
        }
        run {
            val section = database.foodSecurityCopingDao().getByFormIdNonLive(formId)
            batch.setTask(
                mFirestore.collection(FIREBASE_KEY_FOOD_COPING).document(section.id),
                section
            )
        }
        run {
            val section = database.foodSecurityNeedsDao().getByFormIdNonLive(formId)
            batch.setTask(
                mFirestore.collection(FIREBASE_KEY_FOOD_NEEDS).document(section.id),
                section
            )
        }
        run {
            val section = database.foodSecurityAssistanceRowDao().getByFormIdNonLive(formId)
            batch.setTask(
                mFirestore.collection(FIREBASE_KEY_FOOD_ASSISTANCE).document(formId),
                ListWrapper(formId, section)
            )
        }
        run {
            val section = database.foodSecurityGapsDao().getByFormIdNonLive(formId)
            batch.setTask(
                mFirestore.collection(FIREBASE_KEY_FOOD_GAPS).document(section.id),
                section
            )
        }
    }

    private suspend fun submitLivelihoods(
        database: AppDatabase,
        formId: String,
        batch: WriteBatch
    ) {
        run {
            val section = database.incomeBeforeRowDao().getByFormIdNonLive(formId)
            batch.setTask(
                mFirestore.collection(FIREBASE_KEY_INCOME_BEFORE).document(formId),
                ListWrapper(formId, section)
            )
        }
        run {
            val section = database.incomeAfterRowDao().getByFormIdNonLive(formId)
            batch.setTask(
                mFirestore.collection(FIREBASE_KEY_INCOME_AFTER).document(formId),
                ListWrapper(formId, section)
            )
        }
        run {
            val section = database.estimatedDamageRowDao().getByFormIdNonLive(formId)
            batch.setTask(
                mFirestore.collection(FIREBASE_KEY_ESTIMATED_DAMAGE).document(formId),
                ListWrapper(formId, section)
            )
        }
        run {
            val section = database.livelihoodsCopingDao().getByFormIdNonLive(formId)
            batch.setTask(
                mFirestore.collection(FIREBASE_KEY_LIVELIHOODS_COPING).document(section.id),
                section
            )
        }
        run {
            val section = database.livelihoodsNeedsDao().getByFormIdNonLive(formId)
            batch.setTask(
                mFirestore.collection(FIREBASE_KEY_LIVELIHOODS_NEEDS).document(section.id),
                section
            )
        }
        run {
            val section = database.livelihoodsAssistanceRowDao().getByFormIdNonLive(formId)
            batch.setTask(
                mFirestore.collection(FIREBASE_KEY_LIVELIHOODS_ASSISTANCE).document(formId),
                ListWrapper(formId, section)
            )
        }
        run {
            val section = database.livelihoodsGapsDao().getByFormIdNonLive(formId)
            batch.setTask(
                mFirestore.collection(FIREBASE_KEY_LIVELIHOODS_GAPS).document(section.id),
                section
            )
        }
    }

    private suspend fun submitHealthInformation(
        database: AppDatabase,
        formId: String,
        batch: WriteBatch
    ) {
        run {
            val section = database.diseasesRowDao().getByFormIdNonLive(formId)
            batch.setTask(
                mFirestore.collection(FIREBASE_KEY_DISEASES).document(formId),
                ListWrapper(formId, section)
            )
        }
        run {
            val section = database.specialNeedsRowDao().getByFormIdNonLive(formId)
            batch.setTask(
                mFirestore.collection(FIREBASE_KEY_SPECIAL_NEEDS).document(formId),
                ListWrapper(formId, section)
            )
        }
        run {
            val section = database.psychosocialRowDao().getByFormIdNonLive(formId)
            batch.setTask(
                mFirestore.collection(FIREBASE_KEY_PSYCHOSOCIAL).document(formId),
                ListWrapper(formId, section)
            )
        }
        run {
            val section = database.healthCopingDao().getByFormIdNonLive(formId)
            batch.setTask(
                mFirestore.collection(FIREBASE_KEY_HEALTH_COPING).document(section.id),
                section
            )
        }
        run {
            val section = database.healthAssistanceRowDao().getByFormIdNonLive(formId)
            batch.setTask(
                mFirestore.collection(FIREBASE_KEY_HEALTH_ASSISTANCE).document(formId),
                ListWrapper(formId, section)
            )
        }
        run {
            val section = database.healthGapsDao().getByFormIdNonLive(formId)
            batch.setTask(
                mFirestore.collection(FIREBASE_KEY_HEALTH_GAPS).document(section.id),
                section
            )
        }
    }

    private suspend fun submitWashInformation(
        database: AppDatabase,
        formId: String,
        batch: WriteBatch
    ) {
        run {
            val section = database.washConditionsDao().getByFormIdNonLive(formId)
            batch.setTask(
                mFirestore.collection(FIREBASE_KEY_WASH_CONDITIONS).document(section.id),
                section
            )
        }
        run {
            val section = database.washCopingDao().getByFormIdNonLive(formId)
            batch.setTask(
                mFirestore.collection(FIREBASE_KEY_WASH_COPING).document(section.id),
                section
            )
        }
        run {
            val section = database.washAssistanceRowDao().getByFormIdNonLive(formId)
            batch.setTask(
                mFirestore.collection(FIREBASE_KEY_WASH_ASSISTANCE).document(formId),
                ListWrapper(formId, section)
            )
        }
        run {
            val section = database.washGapsDao().getByFormIdNonLive(formId)
            batch.setTask(
                mFirestore.collection(FIREBASE_KEY_WASH_GAPS).document(section.id),
                section
            )
        }
    }

    private suspend fun submitEvacuationInformation(
        database: AppDatabase,
        formId: String,
        batch: WriteBatch
    ) {
        run {
            val section = database.evacuationItemDao().getByFormIdNonLive(formId)
            batch.setTask(
                mFirestore.collection(FIREBASE_KEY_EVACUATION).document(formId),
                ListWrapper(formId, section)
            )
        }
    }

    private suspend fun sendCaseStories(
        database: AppDatabase, formId: String, batch: WriteBatch
    ): Queue<CaseStoriesImageItem> {
        run {
            val section = database.caseStoriesDao().getByFormIdNonLive(formId)
            batch.setTask(
                mFirestore.collection(FIREBASE_KEY_CASE_STORIES).document(section.root!!.id),
                section
            )
            val imagesQueue = LinkedList<CaseStoriesImageItem>()
            section.images?.let {
                imagesQueue.addAll(it)
            }
//            section.images?.forEach {
//                val task = mStorage.reference.child("images/${it.id}")
//                    .putFile(Uri.parse(it.uri))
//                task.addOnSuccessListener { }
//                mUploadTasks.add(task)
//            }
            return imagesQueue
        }
    }

    private suspend fun submitCaseStories(
        database: AppDatabase, formId: String, batch: WriteBatch,
        onProgressListener: (ProgressNotification) -> Any,
        completionListener: () -> Any
    ) {
        run {
            val listener = { progress: ProgressNotification ->
                onProgressListener(progress)
                if (progress == ProgressNotification.IMAGE_UPLOAD_COMPLETED) {
                    completionListener()
                }
            }
            val section = database.caseStoriesDao().getByFormIdNonLive(formId)
            batch.setTask(
                mFirestore.collection(FIREBASE_KEY_CASE_STORIES).document(section.root!!.id),
                section
            )
            if (section.images != null) {
                runWithSuccessCounter(
                    section.images!!.size,
                    listener,
                    ProgressNotification.IMAGE_UPLOAD_COMPLETED
                ) { pn ->
                    section.images!!.forEach {
                        val task = mStorage.reference.child("images/${it.id}")
                            .putFile(Uri.parse(it.uri))
                        task.addOnCanceledListener { onProgressListener(ProgressNotification.CANCELLED) }
                            .addOnFailureListener { onProgressListener(ProgressNotification.ERROR_OCCURRED) }
                            .addOnSuccessListener {
                                onProgressListener(ProgressNotification.IMAGE_UPLOADED)
                                pn()
                            }
                        mUploadTasks.add(task)
                    }
                }
            }
        }
    }

    // endregion

    // region Private methods and classes

    private fun WriteBatch.setTask(ref: DocumentReference, data: Any) {
        this.set(ref, data)
    }

    private suspend fun runWithSuccessCounter(
        targetCount: Int,
        onProgressListener: (ProgressNotification) -> Any,
        progressNotificationOnComplete: ProgressNotification,
        f: suspend (() -> Any) -> Any
    ) {

        var successCount = 0
        if (successCount == targetCount) {
            onProgressListener(progressNotificationOnComplete)
        }

        val successCounter = {
            successCount++
            if (successCount == targetCount) {
                onProgressListener(progressNotificationOnComplete)
            }
        }
        f(successCounter)
    }

    class ListWrapper(val formId: String, val list: List<Any>)

    // endregion
}