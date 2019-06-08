package com.cpu.quikdata.feature.createform

import android.app.Application
import androidx.lifecycle.AndroidViewModel

class CreateFormViewModel(application: Application, formId: String) : AndroidViewModel(application) {

    private val mRepository = CreateFormRepository(application)
    private var mFormId = formId

    val formId: String
        get() = mFormId

    fun submitData() = mRepository.submitData(mFormId)

}