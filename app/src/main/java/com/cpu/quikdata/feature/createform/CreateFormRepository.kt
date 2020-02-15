package com.cpu.quikdata.feature.createform

import android.net.Uri
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import com.cpu.quikdata.common.FirebaseHelper
import com.cpu.quikdata.common.ProgressNotification
import com.cpu.quikdata.common.deleteFile
import com.cpu.quikdata.data.AppDatabase
import com.cpu.quikdata.data.form.Form
import com.cpu.quikdata.di.annotation.FormIdQualifier
import com.cpu.quikdata.utils.getDateTimeNowInLong
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class CreateFormRepository @Inject constructor (private val mDatabase: AppDatabase,
                                                private val mFirebaseHelper: FirebaseHelper,
                                                @FormIdQualifier val formId: String) {

    private val mForm = mDatabase.formDao().getById(formId)
    private val mSaveResult: MediatorLiveData<ProgressNotification> = MediatorLiveData()

    val form: LiveData<Form>
        get() = mForm

    val isFormTemporary: Boolean
        get() = mForm.value!!.isTemporary

    val saveResult: LiveData<ProgressNotification>
        get() = mSaveResult

    suspend fun deleteForm() {
        // Delete image files associated with the form
        val caseStories = mDatabase.caseStoriesDao().getByFormIdNonLive(formId)
        caseStories.images?.forEach {
            Uri.parse(it.uri).deleteFile()
        }

        mForm.value?.let {
            if (it.isTemporary) {
                mDatabase.formDao().delete(it)
            }
        }
    }

    suspend fun saveFormAsActual(isBasicMode: Boolean) {
        performSaveChangesToFormOnly()
        withContext(Dispatchers.Main) {
            val operation = if (isBasicMode) {
                mFirebaseHelper.submitBasicData(formId)
            } else {
                mFirebaseHelper.submitAllData(formId)
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
    }

    suspend fun saveChangesToFormOnly() {
        performSaveChangesToFormOnly()
    }

    private suspend fun performSaveChangesToFormOnly() {
        mForm.value?.let {
            it.isTemporary = false
            it.dateModified = getDateTimeNowInLong()
            mDatabase.formDao().update(it)
        }
    }

    fun cancelSubmission() = mFirebaseHelper.cancelSubmission()

    suspend fun toggleSectionInclusions(includeShelter: Boolean,
                                includeFood: Boolean,
                                includeLivelihoods: Boolean,
                                includeHealth: Boolean,
                                includeWash: Boolean,
                                includeEvacuation: Boolean) {

        val formValue = mForm.value!!
        formValue.includeShelter = includeShelter
        formValue.includeFood = includeFood
        formValue.includeLivelihoods = includeLivelihoods
        formValue.includeHealth = includeHealth
        formValue.includeWash = includeWash
        formValue.includeEvacuation = includeEvacuation
        mDatabase.formDao().update(formValue)
    }
}