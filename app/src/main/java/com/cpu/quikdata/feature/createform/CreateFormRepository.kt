package com.cpu.quikdata.feature.createform

import android.app.Application
import android.net.Uri
import androidx.lifecycle.LiveData
import com.cpu.quikdata.*
import com.cpu.quikdata.common.FirebaseHelper
import com.cpu.quikdata.common.deleteFile
import com.cpu.quikdata.data.AppDatabase
import com.cpu.quikdata.data.form.Form
import com.cpu.quikdata.utils.runOnIoThread
import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import java.io.File

class CreateFormRepository(application: Application, formId: String) {

    private val mDatabase = AppDatabase.get(application)
    private val mFormId = formId
    private val mForm = mDatabase.formDao().getById(mFormId)
    private val mFirebaseHelper = FirebaseHelper()

    val form: LiveData<Form>
        get() = mForm

    val isFormTemporary: Boolean
        get() = mForm.value!!.isTemporary

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

    fun saveFormAsActual() {
        runOnIoThread {
            val formValue = mForm.value!!
            formValue.isTemporary = false
            mDatabase.formDao().update(formValue)
            mFirebaseHelper.submitBasicData(mDatabase, mFormId)
        }
    }
}