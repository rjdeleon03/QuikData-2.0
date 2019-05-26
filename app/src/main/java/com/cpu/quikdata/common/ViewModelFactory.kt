package com.cpu.quikdata.common

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.cpu.quikdata.feature.createform.formdetails.FormDetailsViewModel

@Suppress("UNCHECKED_CAST")
class ViewModelFactory(application: Application, formId: String) :
    ViewModelProvider.AndroidViewModelFactory(application) {

    private val mApplication = application
    private val mFormId = formId

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {

        if (modelClass == FormDetailsViewModel::class.java) {
            return FormDetailsViewModel(mApplication, mFormId) as T
        }
        return FormDetailsViewModel(mApplication, mFormId) as T
    }
}