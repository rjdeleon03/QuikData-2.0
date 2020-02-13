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
import com.cpu.quikdata.utils.runOnIoThread
import com.cpu.quikdata.utils.runOnMainThread
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import javax.inject.Inject

class CreateFormRepository @Inject constructor (private val mDatabase: AppDatabase, @FormIdQualifier val formId: String) {

    private val mForm = mDatabase.formDao().getById(formId)
    private val mFirebaseHelper = FirebaseHelper(FirebaseFirestore.getInstance(), FirebaseStorage.getInstance())
    private val mSaveResult: MediatorLiveData<ProgressNotification> = MediatorLiveData()

    val form: LiveData<Form>
        get() = mForm

    val isFormTemporary: Boolean
        get() = mForm.value!!.isTemporary

    val saveResult: LiveData<ProgressNotification>
        get() = mSaveResult

    fun deleteForm() {
        runOnIoThread {
            // Delete image files associated with the form
            val caseStories = mDatabase.caseStoriesDao().getByFormIdNonLive(formId)
            caseStories.images?.forEach {
                Uri.parse(it.uri).deleteFile()
            }

            val formValue = mForm.value!!
            if (formValue.isTemporary) {
                mDatabase.formDao().delete(formValue)
            }
        }
    }

    fun saveFormAsActual(isBasicMode: Boolean) {
        runOnIoThread {
            performSaveChangesToFormOnly()
            runOnMainThread {
                val operation = if (isBasicMode) {
                    mFirebaseHelper.submitBasicData(mDatabase, formId)
                } else {
                    mFirebaseHelper.submitAllData(mDatabase, formId)
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
    }

    fun saveChangesToFormOnly() {
        runOnIoThread {
            performSaveChangesToFormOnly()
        }
    }

    private fun performSaveChangesToFormOnly() {
        val formValue = mForm.value!!
        formValue.isTemporary = false
        formValue.dateModified = getDateTimeNowInLong()
        mDatabase.formDao().update(formValue)
    }

    fun cancelSubmission() = mFirebaseHelper.cancelSubmission()

    fun toggleSectionInclusions(includeShelter: Boolean,
                                includeFood: Boolean,
                                includeLivelihoods: Boolean,
                                includeHealth: Boolean,
                                includeWash: Boolean,
                                includeEvacuation: Boolean) {
        runOnIoThread {
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
}