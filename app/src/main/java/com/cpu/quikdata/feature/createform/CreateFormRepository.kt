package com.cpu.quikdata.feature.createform

import android.net.Uri
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import com.cpu.quikdata.common.FirebaseHelper
import com.cpu.quikdata.common.ProgressNotification
import com.cpu.quikdata.common.deleteFile
import com.cpu.quikdata.data.AppDatabase
import com.cpu.quikdata.data.casestories.CaseStoriesDao
import com.cpu.quikdata.data.form.Form
import com.cpu.quikdata.data.form.FormDao
import com.cpu.quikdata.di.component.repository.DaggerCreateFormRepositoryComponent
import com.cpu.quikdata.di.module.AppModule
import com.cpu.quikdata.di.module.DatabaseModule
import com.cpu.quikdata.feature.QuikDataApp
import com.cpu.quikdata.utils.getDateTimeNowInLong
import com.cpu.quikdata.utils.runOnIoThread
import com.cpu.quikdata.utils.runOnMainThread
import javax.inject.Inject

class CreateFormRepository(application: QuikDataApp,
                           private val mFormId: String) {

    @Inject
    lateinit var database: AppDatabase

    @Inject
    lateinit var formDao: FormDao

    @Inject
    lateinit var caseStoriesDao: CaseStoriesDao

    @Inject
    lateinit var firebaseHelper: FirebaseHelper

    private val mForm: LiveData<Form>

    private val mSaveResult: MediatorLiveData<ProgressNotification> = MediatorLiveData()

    init {
        DaggerCreateFormRepositoryComponent
            .builder()
            .databaseModule(DatabaseModule(application))
            .build()
            .inject(this)

        mForm = formDao.getById(mFormId)
    }

    val form: LiveData<Form>
        get() = mForm

    val isFormTemporary: Boolean
        get() = mForm.value!!.isTemporary

    val saveResult: LiveData<ProgressNotification>
        get() = mSaveResult

    fun deleteForm() {
        runOnIoThread {
            // Delete image files associated with the form
            val caseStories = caseStoriesDao.getByFormIdNonLive(mFormId)
            caseStories.images?.forEach {
                Uri.parse(it.uri).deleteFile()
            }

            val formValue = mForm.value!!
            if (formValue.isTemporary) {
                formDao.delete(formValue)
            }
        }
    }

    fun saveFormAsActual(isBasicMode: Boolean) {
        runOnIoThread {
            performSaveChangesToFormOnly()
            runOnMainThread {
                val operation = if (isBasicMode) {
                    firebaseHelper.submitBasicData(database, mFormId)
                } else {
                    firebaseHelper.submitAllData(database, mFormId)
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
        formDao.update(formValue)
    }

    fun cancelSubmission() = firebaseHelper.cancelSubmission()

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
            formDao.update(formValue)
        }
    }
}