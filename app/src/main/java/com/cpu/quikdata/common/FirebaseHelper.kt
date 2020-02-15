package com.cpu.quikdata.common

import android.net.Uri
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.cpu.quikdata.*
import com.cpu.quikdata.data.AppDatabase
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.WriteBatch
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.UploadTask
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

enum class ProgressNotification {
    FORM_SUBMITTED,
    IMAGE_UPLOADED,
    IMAGE_UPLOAD_COMPLETED,
    FINISHED,
    ERROR_OCCURRED,
    CANCELLED
}

class FirebaseHelper @Inject constructor(private val mDatabase: AppDatabase,
                                         private val mFirestore: FirebaseFirestore,
                                         private val mStorage: FirebaseStorage) {

    private val mUploadTasks = arrayListOf<UploadTask>()
    @Volatile private var mIsCancelled = false

    fun cancelSubmission() {
        mIsCancelled = true

        // TODO: Remove this afterwards
        // System.out.println("============ CANCELLING SUBMISSION =============")

        cancelUploadTasks()
    }

    private fun cancelUploadTasks() {
        for(it in mUploadTasks) {
            it.cancel()
        }
        mUploadTasks.clear()
    }

    private fun getOnProgressListener(resultLiveData: MutableLiveData<ProgressNotification>):
            (ProgressNotification) -> Unit = { pn: ProgressNotification ->
            if (mIsCancelled) {
                resultLiveData.value = ProgressNotification.CANCELLED
            } else {
                // System.out.println("============ $pn =============")
                when (pn) {
                    ProgressNotification.FINISHED,
                    ProgressNotification.CANCELLED,
                    ProgressNotification.ERROR_OCCURRED -> {
                        if (resultLiveData.value != pn) {
                            resultLiveData.value = pn
                        }
                    }
                    else -> {
                        resultLiveData.value = pn
                    }
                }
            }
    }

    // region Submission methods

    suspend fun submitBasicData(formId: String) : LiveData<ProgressNotification> {
        cancelUploadTasks()
        mIsCancelled = false
        val resultLiveData = MutableLiveData<ProgressNotification>()
        val progressListener = getOnProgressListener(resultLiveData)

        withContext(Dispatchers.IO) {
            runWithSuccessCounter(2, progressListener, ProgressNotification.FINISHED) { pnl ->

                launch(Dispatchers.IO) {
                    val batch = mFirestore.batch()
                    submitFormDetails(formId, batch)
                    submitGeneralInformation(formId, batch)
                    submitCaseStories(formId, batch, progressListener, pnl)
                    batch.commit()
                        .addOnFailureListener { progressListener(ProgressNotification.ERROR_OCCURRED) }
                        .addOnSuccessListener {
                            progressListener(ProgressNotification.FORM_SUBMITTED)
                            pnl()
                        }
                }
            }
        }

        return resultLiveData
    }

    suspend fun submitAllData(formId: String) : LiveData<ProgressNotification> {
        cancelUploadTasks()
        mIsCancelled = false
        val resultLiveData = MutableLiveData<ProgressNotification>()
        val progressListener = getOnProgressListener(resultLiveData)

        withContext(Dispatchers.IO) {
            val formData = mDatabase.formDao().getFormDataNonLive(formId)
            runWithSuccessCounter(2, progressListener, ProgressNotification.FINISHED) { pnl ->
                launch(Dispatchers.IO) {
                    val batch = mFirestore.batch()
                    submitFormDetails(formId, batch)
                    submitGeneralInformation(formId, batch)
                    submitCaseStories(formId, batch, progressListener, pnl)

                    formData.form?.includeShelter?.let {
                        submitShelterInformation(
                            formId,
                            batch
                        )
                    }
                    formData.form?.includeFood?.let { submitFoodSecurity(formId, batch) }
                    formData.form?.includeLivelihoods?.let {
                        submitLivelihoods(
                            formId,
                            batch
                        )
                    }
                    formData.form?.includeHealth?.let {
                        submitHealthInformation(
                            formId,
                            batch
                        )
                    }
                    formData.form?.includeWash?.let {
                        submitWashInformation(
                            formId,
                            batch
                        )
                    }
                    formData.form?.includeEvacuation?.let {
                        submitEvacuationInformation(
                            formId,
                            batch
                        )
                    }

                    batch.commit()
                        .addOnFailureListener { progressListener(ProgressNotification.ERROR_OCCURRED) }
                        .addOnSuccessListener {
                            progressListener(ProgressNotification.FORM_SUBMITTED)
                            pnl()
                        }
                }
            }
        }

        return resultLiveData
    }

    private suspend fun submitFormDetails(formId: String, batch:WriteBatch) {
        val section = mDatabase.formDao().getFormDataNonLive(formId)
        batch.setTask(mFirestore.collection(FIREBASE_KEY_FORM).document(section.form!!.id), section)
    }

    private fun submitGeneralInformation(formId: String, batch:WriteBatch) {
        run {
            val section = mDatabase.calamityInfoDao().getByFormIdNonLive(formId)
            batch.setTask(mFirestore.collection(FIREBASE_KEY_CALAMITY_INFO).document(section.id), section)
        }
        run {
            val section = mDatabase.populationRowDao().getByFormIdNonLive(formId)
            batch.setTask(mFirestore.collection(FIREBASE_KEY_POPULATION).document(formId), ListWrapper(formId, section))
        }
        run {
            val section = mDatabase.familiesDao().getByFormIdNonLive(formId)
            batch.setTask(mFirestore.collection(FIREBASE_KEY_FAMILIES).document(section.id), section)
        }
        run {
            val section = mDatabase.vulnerableRowDao().getByFormIdNonLive(formId)
            batch.setTask(mFirestore.collection(FIREBASE_KEY_VULNERABLE).document(formId), ListWrapper(formId, section))
        }
        run {
            val section = mDatabase.casualtiesRowDao().getByFormIdNonLive(formId)
            batch.setTask(mFirestore.collection(FIREBASE_KEY_CASUALTIES).document(formId), ListWrapper(formId, section))
        }
        run {
            val section = mDatabase.causeOfDeathRowDao().getByFormIdNonLive(formId)
            batch.setTask(mFirestore.collection(FIREBASE_KEY_CAUSE_OF_DEATH).document(formId), ListWrapper(formId, section))
        }
        run {
            val section = mDatabase.infrastructureDamageRowDao().getByFormIdNonLive(formId)
            batch.setTask(mFirestore.collection(FIREBASE_KEY_INFRASTRUCTURE).document(formId), ListWrapper(formId, section))
        }
    }

    private fun submitShelterInformation(formId: String, batch:WriteBatch) {
        run {
            val section = mDatabase.houseDamageRowDao().getByFormIdNonLive(formId)
            batch.setTask(mFirestore.collection(FIREBASE_KEY_HOUSE_DAMAGE).document(formId), ListWrapper(formId, section))
        }
        run {
            val section = mDatabase.shelterCopingDao().getByFormIdNonLive(formId)
            batch.setTask(mFirestore.collection(FIREBASE_KEY_SHELTER_COPING).document(section.id), section)
        }
        run {
            val section = mDatabase.shelterNeedsRowDao().getByFormIdNonLive(formId)
            batch.setTask(mFirestore.collection(FIREBASE_KEY_SHELTER_NEEDS).document(formId), ListWrapper(formId, section))
        }
        run {
            val section = mDatabase.shelterAssistanceRowDao().getByFormIdNonLive(formId)
            batch.setTask(mFirestore.collection(FIREBASE_KEY_SHELTER_ASSISTANCE).document(formId), ListWrapper(formId, section))
        }
        run {
            val section = mDatabase.shelterGapsDao().getByFormIdNonLive(formId)
            batch.setTask(mFirestore.collection(FIREBASE_KEY_SHELTER_GAPS).document(section.id), section)
        }
    }

    private fun submitFoodSecurity(formId: String, batch: WriteBatch) {
        run {
            val section = mDatabase.foodSecurityImpactDao().getByFormIdNonLive(formId)
            batch.setTask(mFirestore.collection(FIREBASE_KEY_FOOD_IMPACT).document(section.id), section)
        }
        run {
            val section = mDatabase.foodSecurityCopingDao().getByFormIdNonLive(formId)
            batch.setTask(mFirestore.collection(FIREBASE_KEY_FOOD_COPING).document(section.id), section)
        }
        run {
            val section = mDatabase.foodSecurityNeedsDao().getByFormIdNonLive(formId)
            batch.setTask(mFirestore.collection(FIREBASE_KEY_FOOD_NEEDS).document(section.id), section)
        }
        run {
            val section = mDatabase.foodSecurityAssistanceRowDao().getByFormIdNonLive(formId)
            batch.setTask(mFirestore.collection(FIREBASE_KEY_FOOD_ASSISTANCE).document(formId), ListWrapper(formId, section))
        }
        run {
            val section = mDatabase.foodSecurityGapsDao().getByFormIdNonLive(formId)
            batch.setTask(mFirestore.collection(FIREBASE_KEY_FOOD_GAPS).document(section.id), section)
        }
    }

    private fun submitLivelihoods(formId: String, batch: WriteBatch) {
        run {
            val section = mDatabase.incomeBeforeRowDao().getByFormIdNonLive(formId)
            batch.setTask(mFirestore.collection(FIREBASE_KEY_INCOME_BEFORE).document(formId), ListWrapper(formId, section))
        }
        run {
            val section = mDatabase.incomeAfterRowDao().getByFormIdNonLive(formId)
            batch.setTask(mFirestore.collection(FIREBASE_KEY_INCOME_AFTER).document(formId), ListWrapper(formId, section))
        }
        run {
            val section = mDatabase.estimatedDamageRowDao().getByFormIdNonLive(formId)
            batch.setTask(mFirestore.collection(FIREBASE_KEY_ESTIMATED_DAMAGE).document(formId), ListWrapper(formId, section))
        }
        run {
            val section = mDatabase.livelihoodsCopingDao().getByFormIdNonLive(formId)
            batch.setTask(mFirestore.collection(FIREBASE_KEY_LIVELIHOODS_COPING).document(section.id), section)
        }
        run {
            val section = mDatabase.livelihoodsNeedsDao().getByFormIdNonLive(formId)
            batch.setTask(mFirestore.collection(FIREBASE_KEY_LIVELIHOODS_NEEDS).document(section.id), section)
        }
        run {
            val section = mDatabase.livelihoodsAssistanceRowDao().getByFormIdNonLive(formId)
            batch.setTask(mFirestore.collection(FIREBASE_KEY_LIVELIHOODS_ASSISTANCE).document(formId), ListWrapper(formId, section))
        }
        run {
            val section = mDatabase.livelihoodsGapsDao().getByFormIdNonLive(formId)
            batch.setTask(mFirestore.collection(FIREBASE_KEY_LIVELIHOODS_GAPS).document(section.id), section)
        }
    }

    private fun submitHealthInformation(formId: String, batch: WriteBatch) {
        run {
            val section = mDatabase.diseasesRowDao().getByFormIdNonLive(formId)
            batch.setTask(mFirestore.collection(FIREBASE_KEY_DISEASES).document(formId), ListWrapper(formId, section))
        }
        run {
            val section = mDatabase.specialNeedsRowDao().getByFormIdNonLive(formId)
            batch.setTask(mFirestore.collection(FIREBASE_KEY_SPECIAL_NEEDS).document(formId), ListWrapper(formId, section))
        }
        run {
            val section = mDatabase.psychosocialRowDao().getByFormIdNonLive(formId)
            batch.setTask(mFirestore.collection(FIREBASE_KEY_PSYCHOSOCIAL).document(formId), ListWrapper(formId, section))
        }
        run {
            val section = mDatabase.healthCopingDao().getByFormIdNonLive(formId)
            batch.setTask(mFirestore.collection(FIREBASE_KEY_HEALTH_COPING).document(section.id), section)
        }
        run {
            val section = mDatabase.healthAssistanceRowDao().getByFormIdNonLive(formId)
            batch.setTask(mFirestore.collection(FIREBASE_KEY_HEALTH_ASSISTANCE).document(formId), ListWrapper(formId, section))
        }
        run {
            val section = mDatabase.healthGapsDao().getByFormIdNonLive(formId)
            batch.setTask(mFirestore.collection(FIREBASE_KEY_HEALTH_GAPS).document(section.id), section)
        }
    }

    private fun submitWashInformation(formId: String, batch: WriteBatch) {
        run {
            val section = mDatabase.washConditionsDao().getByFormIdNonLive(formId)
            batch.setTask(mFirestore.collection(FIREBASE_KEY_WASH_CONDITIONS).document(section.id), section)
        }
        run {
            val section = mDatabase.washCopingDao().getByFormIdNonLive(formId)
            batch.setTask(mFirestore.collection(FIREBASE_KEY_WASH_COPING).document(section.id), section)
        }
        run {
            val section = mDatabase.washAssistanceRowDao().getByFormIdNonLive(formId)
            batch.setTask(mFirestore.collection(FIREBASE_KEY_WASH_ASSISTANCE).document(formId), ListWrapper(formId, section))
        }
        run {
            val section = mDatabase.washGapsDao().getByFormIdNonLive(formId)
            batch.setTask(mFirestore.collection(FIREBASE_KEY_WASH_GAPS).document(section.id), section)
        }
    }

    private fun submitEvacuationInformation(formId: String, batch: WriteBatch) {
        run {
            val section = mDatabase.evacuationItemDao().getByFormIdNonLive(formId)
            batch.setTask(mFirestore.collection(FIREBASE_KEY_EVACUATION).document(formId), ListWrapper(formId, section))
        }
    }

    private fun submitCaseStories(formId: String, batch: WriteBatch,
                                  onProgressListener: (ProgressNotification) -> Any,
                                  completionListener: () -> Any) {
        run {
            val listener: (ProgressNotification) -> Any = { progress : ProgressNotification ->
                onProgressListener(progress)
                if (progress == ProgressNotification.IMAGE_UPLOAD_COMPLETED) {
                    completionListener()
                }
            }
            val section = mDatabase.caseStoriesDao().getByFormIdNonLive(formId)
            batch.setTask(mFirestore.collection(FIREBASE_KEY_CASE_STORIES).document(section.root!!.id), section)
            if (section.images != null) {
                runWithSuccessCounter(section.images!!.size, listener, ProgressNotification.IMAGE_UPLOAD_COMPLETED) { pn ->
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

    private fun runWithSuccessCounter(targetCount: Int,
                                      onProgressListener: (ProgressNotification) -> Any,
                                      progressNotificationOnComplete: ProgressNotification,
                                      f: (() -> Any) -> Any) {

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