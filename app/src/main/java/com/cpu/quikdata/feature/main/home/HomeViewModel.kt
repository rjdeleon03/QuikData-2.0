package com.cpu.quikdata.feature.main.home

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.cpu.quikdata.feature.main.newforms.NewFormsRepository

class HomeViewModel(application: Application) : AndroidViewModel(application) {

    private val mRepository = NewFormsRepository(application)

    fun createNewForm(formId: String) = mRepository.createNewForm(formId)
}