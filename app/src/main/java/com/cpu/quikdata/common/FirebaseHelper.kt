package com.cpu.quikdata.common

import android.net.Uri
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.cpu.quikdata.*
import com.cpu.quikdata.data.AppDatabase
import com.cpu.quikdata.utils.runOnIoThread
import com.cpu.quikdata.utils.runOnMainThread
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.WriteBatch
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.UploadTask

class FirebaseHelper {

    private val mFirestore = FirebaseFirestore.getInstance()
    private val mStorage = FirebaseStorage.getInstance()
    private val mUploadTasks = arrayListOf<UploadTask>()
    @Volatile private var mIsCancelled = false

    fun cancelSubmission() {
        mIsCancelled = true
        System.out.println("============ CANCELLING SUBMISSION =============")
        cancelUploadTasks()
    }

    private fun cancelUploadTasks() {
        mUploadTasks.forEach {
            it.cancel()
        }
        mUploadTasks.clear()
    }

    // region Submission methods

    fun submitBasicData(database: AppDatabase, formId: String) : LiveData<Boolean?> {
        cancelUploadTasks()
        val resultLiveData = MutableLiveData<Boolean?>()
        val onSuccessListener = {
            runOnMainThread {
                if (mIsCancelled) {
                    resultLiveData.value = null
                } else if (resultLiveData.value != true) {
                    resultLiveData.value = true
                }
            }
        }
        val onFailureListener = {
            runOnMainThread {
                if (resultLiveData.value != false) resultLiveData.value = false
            }
        }

        runOnIoThread {
            runWithSuccessCounter(2, onSuccessListener) {
                val batch = mFirestore.batch()
                submitFormDetails(database, formId, batch)
                submitGeneralInformation(database, formId, batch)
                submitCaseStories(database, formId, batch, onSuccessListener)
                batch.commit().addOnSuccessListener { onSuccessListener() }
                    .addOnFailureListener { onFailureListener() }
            }
        }

        return resultLiveData
    }

    fun submitAllData(database: AppDatabase, formId: String) : LiveData<Boolean?> {
        cancelUploadTasks()
        val resultLiveData = MutableLiveData<Boolean?>()
        val onSuccessListener = {
            runOnMainThread {
                if (mIsCancelled) {
                    resultLiveData.value = null
                }
                else if (resultLiveData.value != true) {
                    resultLiveData.value = true
                }
            }
        }
        val onFailureListener = {
            runOnMainThread {
                if (resultLiveData.value != false) resultLiveData.value = false
            }
        }

        runOnIoThread {
            runWithSuccessCounter(2, onSuccessListener) {
                val batch = mFirestore.batch()
                submitFormDetails(database, formId, batch)
                submitGeneralInformation(database, formId, batch)
                submitShelterInformation(database, formId, batch)
                submitFoodSecurity(database, formId, batch)
                submitLivelihoods(database, formId, batch)
                submitHealthInformation(database, formId, batch)
                submitWashInformation(database, formId, batch)
                submitEvacuationInformation(database, formId, batch)
                submitCaseStories(database, formId, batch, onSuccessListener)
                batch.commit().addOnSuccessListener { onSuccessListener() }
                    .addOnFailureListener { onFailureListener() }
            }
        }

        return resultLiveData
    }

    private fun submitFormDetails(database: AppDatabase, formId: String, batch:WriteBatch) {
        run {
            val section = database.formDao().getFormDataNonLive(formId)
            batch.setTask(mFirestore.collection(FIREBASE_KEY_FORM).document(section.form!!.id), section)
        }
    }

    private fun submitGeneralInformation(database: AppDatabase, formId: String, batch:WriteBatch) {
        run {
            val section = database.calamityInfoDao().getByFormIdNonLive(formId)
            batch.setTask(mFirestore.collection(FIREBASE_KEY_CALAMITY_INFO).document(section.id), section)
        }
        run {
            val section = database.populationRowDao().getByFormIdNonLive(formId)
            batch.setTask(mFirestore.collection(FIREBASE_KEY_POPULATION).document(formId), ListWrapper(formId, section))
        }
        run {
            val section = database.familiesDao().getByFormIdNonLive(formId)
            batch.setTask(mFirestore.collection(FIREBASE_KEY_FAMILIES).document(section.id), section)
        }
        run {
            val section = database.vulnerableRowDao().getByFormIdNonLive(formId)
            batch.setTask(mFirestore.collection(FIREBASE_KEY_VULNERABLE).document(formId), ListWrapper(formId, section))
        }
        run {
            val section = database.casualtiesRowDao().getByFormIdNonLive(formId)
            batch.setTask(mFirestore.collection(FIREBASE_KEY_CASUALTIES).document(formId), ListWrapper(formId, section))
        }
        run {
            val section = database.causeOfDeathRowDao().getByFormIdNonLive(formId)
            batch.setTask(mFirestore.collection(FIREBASE_KEY_CAUSE_OF_DEATH).document(formId), ListWrapper(formId, section))
        }
        run {
            val section = database.infrastructureDamageRowDao().getByFormIdNonLive(formId)
            batch.setTask(mFirestore.collection(FIREBASE_KEY_INFRASTRUCTURE).document(formId), ListWrapper(formId, section))
        }
    }

    private fun submitShelterInformation(database: AppDatabase, formId: String, batch:WriteBatch) {
        run {
            val section = database.houseDamageRowDao().getByFormIdNonLive(formId)
            batch.setTask(mFirestore.collection(FIREBASE_KEY_HOUSE_DAMAGE).document(formId), ListWrapper(formId, section))
        }
        run {
            val section = database.shelterCopingDao().getByFormIdNonLive(formId)
            batch.setTask(mFirestore.collection(FIREBASE_KEY_SHELTER_COPING).document(section.id), section)
        }
        run {
            val section = database.shelterNeedsRowDao().getByFormIdNonLive(formId)
            batch.setTask(mFirestore.collection(FIREBASE_KEY_SHELTER_NEEDS).document(formId), ListWrapper(formId, section))
        }
        run {
            val section = database.shelterAssistanceRowDao().getByFormIdNonLive(formId)
            batch.setTask(mFirestore.collection(FIREBASE_KEY_SHELTER_ASSISTANCE).document(formId), ListWrapper(formId, section))
        }
        run {
            val section = database.shelterGapsDao().getByFormIdNonLive(formId)
            batch.setTask(mFirestore.collection(FIREBASE_KEY_SHELTER_GAPS).document(section.id), section)
        }
    }

    private fun submitFoodSecurity(database: AppDatabase, formId: String, batch: WriteBatch) {
        run {
            val section = database.foodSecurityImpactDao().getByFormIdNonLive(formId)
            batch.setTask(mFirestore.collection(FIREBASE_KEY_FOOD_IMPACT).document(section.id), section)
        }
        run {
            val section = database.foodSecurityCopingDao().getByFormIdNonLive(formId)
            batch.setTask(mFirestore.collection(FIREBASE_KEY_FOOD_COPING).document(section.id), section)
        }
        run {
            val section = database.foodSecurityNeedsDao().getByFormIdNonLive(formId)
            batch.setTask(mFirestore.collection(FIREBASE_KEY_FOOD_NEEDS).document(section.id), section)
        }
        run {
            val section = database.foodSecurityAssistanceRowDao().getByFormIdNonLive(formId)
            batch.setTask(mFirestore.collection(FIREBASE_KEY_FOOD_ASSISTANCE).document(formId), ListWrapper(formId, section))
        }
        run {
            val section = database.foodSecurityGapsDao().getByFormIdNonLive(formId)
            batch.setTask(mFirestore.collection(FIREBASE_KEY_FOOD_GAPS).document(section.id), section)
        }
    }

    private fun submitLivelihoods(database: AppDatabase, formId: String, batch: WriteBatch) {
        run {
            val section = database.incomeBeforeRowDao().getByFormIdNonLive(formId)
            batch.setTask(mFirestore.collection(FIREBASE_KEY_INCOME_BEFORE).document(formId), ListWrapper(formId, section))
        }
        run {
            val section = database.incomeAfterRowDao().getByFormIdNonLive(formId)
            batch.setTask(mFirestore.collection(FIREBASE_KEY_INCOME_AFTER).document(formId), ListWrapper(formId, section))
        }
        run {
            val section = database.estimatedDamageRowDao().getByFormIdNonLive(formId)
            batch.setTask(mFirestore.collection(FIREBASE_KEY_ESTIMATED_DAMAGE).document(formId), ListWrapper(formId, section))
        }
        run {
            val section = database.livelihoodsCopingDao().getByFormIdNonLive(formId)
            batch.setTask(mFirestore.collection(FIREBASE_KEY_LIVELIHOODS_COPING).document(section.id), section)
        }
        run {
            val section = database.livelihoodsNeedsDao().getByFormIdNonLive(formId)
            batch.setTask(mFirestore.collection(FIREBASE_KEY_LIVELIHOODS_NEEDS).document(section.id), section)
        }
        run {
            val section = database.livelihoodsAssistanceRowDao().getByFormIdNonLive(formId)
            batch.setTask(mFirestore.collection(FIREBASE_KEY_LIVELIHOODS_ASSISTANCE).document(formId), ListWrapper(formId, section))
        }
        run {
            val section = database.livelihoodsGapsDao().getByFormIdNonLive(formId)
            batch.setTask(mFirestore.collection(FIREBASE_KEY_LIVELIHOODS_GAPS).document(section.id), section)
        }
    }

    private fun submitHealthInformation(database: AppDatabase, formId: String, batch: WriteBatch) {
        run {
            val section = database.diseasesRowDao().getByFormIdNonLive(formId)
            batch.setTask(mFirestore.collection(FIREBASE_KEY_DISEASES).document(formId), ListWrapper(formId, section))
        }
        run {
            val section = database.specialNeedsRowDao().getByFormIdNonLive(formId)
            batch.setTask(mFirestore.collection(FIREBASE_KEY_SPECIAL_NEEDS).document(formId), ListWrapper(formId, section))
        }
        run {
            val section = database.psychosocialRowDao().getByFormIdNonLive(formId)
            batch.setTask(mFirestore.collection(FIREBASE_KEY_PSYCHOSOCIAL).document(formId), ListWrapper(formId, section))
        }
        run {
            val section = database.healthCopingDao().getByFormIdNonLive(formId)
            batch.setTask(mFirestore.collection(FIREBASE_KEY_HEALTH_COPING).document(section.id), section)
        }
        run {
            val section = database.healthAssistanceRowDao().getByFormIdNonLive(formId)
            batch.setTask(mFirestore.collection(FIREBASE_KEY_HEALTH_ASSISTANCE).document(formId), ListWrapper(formId, section))
        }
        run {
            val section = database.healthGapsDao().getByFormIdNonLive(formId)
            batch.setTask(mFirestore.collection(FIREBASE_KEY_HEALTH_GAPS).document(section.id), section)
        }
    }

    private fun submitWashInformation(database: AppDatabase, formId: String, batch: WriteBatch) {
        run {
            val section = database.washConditionsDao().getByFormIdNonLive(formId)
            batch.setTask(mFirestore.collection(FIREBASE_KEY_WASH_CONDITIONS).document(section.id), section)
        }
        run {
            val section = database.washCopingDao().getByFormIdNonLive(formId)
            batch.setTask(mFirestore.collection(FIREBASE_KEY_WASH_COPING).document(section.id), section)
        }
        run {
            val section = database.washAssistanceRowDao().getByFormIdNonLive(formId)
            batch.setTask(mFirestore.collection(FIREBASE_KEY_WASH_ASSISTANCE).document(formId), ListWrapper(formId, section))
        }
        run {
            val section = database.washGapsDao().getByFormIdNonLive(formId)
            batch.setTask(mFirestore.collection(FIREBASE_KEY_WASH_GAPS).document(section.id), section)
        }
    }

    private fun submitEvacuationInformation(database: AppDatabase, formId: String, batch: WriteBatch) {
        run {
            val section = database.evacuationItemDao().getByFormIdNonLive(formId)
            batch.setTask(mFirestore.collection(FIREBASE_KEY_EVACUATION).document(formId), ListWrapper(formId, section))
        }
    }

    private fun submitCaseStories(database: AppDatabase, formId: String, batch: WriteBatch, onSuccessListener: () -> Unit) {
        run {
            val section = database.caseStoriesDao().getByFormIdNonLive(formId)
            batch.setTask(mFirestore.collection(FIREBASE_KEY_CASE_STORIES).document(section.root!!.id), section)
            if (section.images != null) {
                runWithSuccessCounter(section.images!!.size, onSuccessListener) { sc ->
                    section.images!!.forEach {
                        val task = mStorage.reference.child("images/${it.id}")
                            .putFile(Uri.parse(it.uri))
                        task.addOnSuccessListener { sc() }.addOnCanceledListener { sc() }
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

    private fun runWithSuccessCounter(targetCount: Int, onSuccessListener: () -> Any, f: (() -> Any) -> Any) {

        var successCount = 0
        val successCounter = {
            successCount++
            if (successCount == targetCount) {
                onSuccessListener()
            }
        }
        f(successCounter)
    }

    class ListWrapper(val formId: String, val list: List<Any>)

    // endregion
}