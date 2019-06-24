package com.cpu.quikdata.feature.main.newforms

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.cpu.quikdata.data.form.Form
import com.cpu.quikdata.data.form.FormComplete

class NewFormsViewModel(application: Application) : AndroidViewModel(application) {

    private val mRepository = NewFormsRepository(application)

    val newForms: LiveData<List<FormComplete>>
        get() = mRepository.newForms

    fun createNewForm(formId: String) = mRepository.createNewForm(formId)

}
