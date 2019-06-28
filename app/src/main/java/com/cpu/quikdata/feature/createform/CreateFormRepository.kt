package com.cpu.quikdata.feature.createform

import android.app.Application
import android.net.Uri
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import com.cpu.quikdata.common.FirebaseHelper
import com.cpu.quikdata.common.ProgressNotification
import com.cpu.quikdata.common.deleteFile
import com.cpu.quikdata.data.AppDatabase
import com.cpu.quikdata.data.form.Form
import com.cpu.quikdata.utils.runOnIoThread
import com.cpu.quikdata.utils.runOnMainThread

class CreateFormRepository(application: Application, formId: String) {

    private val mDatabase = AppDatabase.get(application)
    private val mFormId = formId
    private val mForm = mDatabase.formDao().getById(mFormId)
    private val mFirebaseHelper = FirebaseHelper()
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
            val caseStories = mDatabase.caseStoriesDao().getByFormIdNonLive(mFormId)
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
            val formValue = mForm.value!!
            formValue.isTemporary = false
            mDatabase.formDao().update(formValue)

            runOnMainThread {
                val operation = if (isBasicMode) {
                    mFirebaseHelper.submitBasicData(mDatabase, mFormId)
                } else {
                    mFirebaseHelper.submitAllData(mDatabase, mFormId)
                }
                mSaveResult.addSource(operation) {
                    mSaveResult.value = it
                    mSaveResult.removeSource(operation)
                }
            }
        }
    }

    fun cancelSubmission() = mFirebaseHelper.cancelSubmission()
}