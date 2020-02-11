package com.cpu.quikdata.feature.main.newforms

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.cpu.quikdata.common.ProgressNotification
import com.cpu.quikdata.data.form.FormComplete
import javax.inject.Inject

class NewFormsViewModel @Inject constructor (application: Application)
    : AndroidViewModel(application) {

    private val mRepository = NewFormsRepository(application)

    val newForms: LiveData<List<FormComplete>>
        get() = mRepository.newForms

    val saveResult: LiveData<ProgressNotification>
        get() = mRepository.saveResult

    fun createNewForm(formId: String) = mRepository.createNewForm(formId)

    fun deleteForm(formComplete: FormComplete) = mRepository.deleteForm(formComplete)

    fun submitForm(formId: String) = mRepository.submitForm(formId)

    fun cancelSubmission() = mRepository.cancelSubmission()

}
