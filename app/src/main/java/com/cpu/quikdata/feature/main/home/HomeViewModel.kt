package com.cpu.quikdata.feature.main.home

import androidx.lifecycle.ViewModel
import com.cpu.quikdata.feature.main.newforms.NewFormsRepository
import javax.inject.Inject

class HomeViewModel @Inject constructor(private val mRepository: NewFormsRepository)
    : ViewModel() {

    fun createNewForm(formId: String) = mRepository.createNewForm(formId)
}