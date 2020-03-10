package com.cpu.quikdata.feature.createform.activity

import android.net.Uri
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import com.cpu.quikdata.common.helper.FirebaseHelper
import com.cpu.quikdata.common.helper.ProgressNotification
import com.cpu.quikdata.common.deleteFile
import com.cpu.quikdata.data.AppDatabase
import com.cpu.quikdata.data.form.Form
import com.cpu.quikdata.utils.getDateTimeNowInLong
import javax.inject.Inject

class CreateFormRepository @Inject constructor(
    private val mFirebaseHelper: FirebaseHelper,
    private val mDatabase: AppDatabase,
    private val mFormId: String
) {

    private val mForm = mDatabase.formDao().getById(mFormId)
    private val mSaveResult: MediatorLiveData<ProgressNotification> = MediatorLiveData()

    val formId: String
        get() = mFormId

    val form: LiveData<Form>
        get() = mForm

    val isFormTemporary: Boolean
        get() = mForm.value!!.isTemporary

    val saveResult: LiveData<ProgressNotification>
        get() = mSaveResult

    suspend fun deleteForm() {
        // Delete image files associated with the form
        val caseStories = mDatabase.caseStoriesDao().getByFormIdNonLive(mFormId)
        caseStories.images?.forEach {
            Uri.parse(it.uri).deleteFile()
        }

        val formValue = mForm.value!!
        if (formValue.isTemporary) {
            mDatabase.formDao().delete(formValue)
        }
    }

    /*
    suspend fun saveFormAsActual(isBasicMode: Boolean) {
        performSaveChangesToFormOnly()
        val operation = if (isBasicMode) {
            mFirebaseHelper.submitBasicData(mFormId)
        } else {
            mFirebaseHelper.submitAllData(mFormId)
        }
        mSaveResult.addSource(operation) {
            mSaveResult.value = it
            if (it == ProgressNotification.FINISHED ||
                it == ProgressNotification.CANCELLED ||
                it == ProgressNotification.ERROR_OCCURRED) {
                mSaveResult.removeSource(operation)
            }
        }
    }

    private suspend fun performSaveChangesToFormOnly() {
        mForm.value?.apply {
            isTemporary = false
            dateModified = getDateTimeNowInLong()
            mDatabase.formDao().update(this)
        }
    }
    */

    suspend fun saveChangesToFormOnly() {
        mForm.value?.apply {
            isTemporary = false
            dateModified = getDateTimeNowInLong()
            mDatabase.formDao().update(this)
        }
    }

    fun cancelSubmission() = mFirebaseHelper.cancelSubmission()

    suspend fun toggleSectionInclusions(
        includeShelter: Boolean,
        includeFood: Boolean,
        includeLivelihoods: Boolean,
        includeHealth: Boolean,
        includeWash: Boolean,
        includeEvacuation: Boolean
    ) {
        mForm.value?.apply {
            this.includeShelter = includeShelter
            this.includeFood = includeFood
            this.includeLivelihoods = includeLivelihoods
            this.includeHealth = includeHealth
            this.includeWash = includeWash
            this.includeEvacuation = includeEvacuation
            mDatabase.formDao().update(this)
        }
    }
}