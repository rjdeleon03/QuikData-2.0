package com.cpu.quikdata.feature.createform

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.cpu.quikdata.common.ProgressNotification
import com.cpu.quikdata.common.runOnIoThread
import com.cpu.quikdata.data.form.Form
import javax.inject.Inject

class CreateFormViewModel @Inject constructor (private val mRepository: CreateFormRepository)
    : ViewModel() {

    val formId: String
        get() = mRepository.formId

    val form: LiveData<Form>
        get() = mRepository.form

    val isFormTemporary: Boolean
        get() = mRepository.isFormTemporary

    val saveResult: LiveData<ProgressNotification>
        get() = mRepository.saveResult

    fun deleteForm() =
        runOnIoThread { mRepository.deleteForm() }

    fun saveFormAsActual(isBasicMode: Boolean = false) =
        runOnIoThread { mRepository.saveFormAsActual(isBasicMode) }

    fun saveChangesToFormOnly() =
        runOnIoThread { mRepository.saveChangesToFormOnly() }

    fun cancelSubmission() = mRepository.cancelSubmission()

    fun toggleSectionInclusions(includeShelter: Boolean,
                                includeFood: Boolean,
                                includeLivelihoods: Boolean,
                                includeHealth: Boolean,
                                includeWash: Boolean,
                                includeEvacuation: Boolean) =
        runOnIoThread {
            mRepository.toggleSectionInclusions(includeShelter, includeFood, includeLivelihoods, includeHealth, includeWash, includeEvacuation)
        }
}