package com.cpu.quikdata.feature.createform

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.cpu.quikdata.data.form.Form

class CreateFormViewModel(application: Application, formId: String) : AndroidViewModel(application) {

    private var mFormId = formId
    private val mRepository = CreateFormRepository(application, mFormId)

    val formId: String
        get() = mFormId

    val form: LiveData<Form>
        get() = mRepository.form

    fun submitFormDetails() = mRepository.submitFormDetails()

    fun submitGeneralInformation() = mRepository.submitGeneralInformation()

    fun submitShelterInformation() = mRepository.submitShelterInformation()

    fun submitFoodSecurity() = mRepository.submitFoodSecurity()

    fun submitLivelihoods() = mRepository.submitLivelihoods()
}