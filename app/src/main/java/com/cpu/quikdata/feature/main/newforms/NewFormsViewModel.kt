package com.cpu.quikdata.feature.main.newforms

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.cpu.quikdata.common.ProgressNotification
import com.cpu.quikdata.common.runOnIoThread
import com.cpu.quikdata.data.form.FormComplete
import javax.inject.Inject

class NewFormsViewModel @Inject constructor (private val mRepository: NewFormsRepository)
    : ViewModel() {

    val newForms: LiveData<List<FormComplete>>
        get() = mRepository.newForms

    val saveResult: LiveData<ProgressNotification>
        get() = mRepository.saveResult

    fun createNewForm(formId: String) =
        runOnIoThread { mRepository.createNewForm(formId) }

    fun deleteForm(formComplete: FormComplete) =
        runOnIoThread { mRepository.deleteForm(formComplete) }

    fun submitForm(formId: String) =
        runOnIoThread { mRepository.submitForm(formId) }

    fun cancelSubmission() = mRepository.cancelSubmission()

}
