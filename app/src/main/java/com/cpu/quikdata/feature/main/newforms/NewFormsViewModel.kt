package com.cpu.quikdata.feature.main.newforms

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.cpu.quikdata.common.ProgressNotification
import com.cpu.quikdata.data.form.FormComplete
import javax.inject.Inject

class NewFormsViewModel @Inject constructor (private val repository: NewFormsRepository)
    : ViewModel() {

    val newForms: LiveData<List<FormComplete>>
        get() = repository.newForms

    val saveResult: LiveData<ProgressNotification>
        get() = repository.saveResult

    fun createNewForm(formId: String) = repository.createNewForm(formId)

    fun deleteForm(formComplete: FormComplete) = repository.deleteForm(formComplete)

    fun submitForm(formId: String) = repository.submitForm(formId)

    fun cancelSubmission() = repository.cancelSubmission()

}
