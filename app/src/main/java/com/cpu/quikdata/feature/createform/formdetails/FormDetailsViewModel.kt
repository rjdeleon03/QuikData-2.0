package com.cpu.quikdata.feature.createform.formdetails

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.cpu.quikdata.data.formdetails.FormDetails

class FormDetailsViewModel(application: Application, formId: String) : AndroidViewModel(application) {

    private val mRepository = FormDetailsRepository(application, formId)

    val formDetails : LiveData<FormDetails>
        get() = mRepository.formDetails

    fun updateFormDetails(formDetails: FormDetails) = mRepository.updateFormDetails(formDetails)

}
