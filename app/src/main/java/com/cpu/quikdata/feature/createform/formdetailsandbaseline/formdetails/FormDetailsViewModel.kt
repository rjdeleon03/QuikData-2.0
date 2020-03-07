package com.cpu.quikdata.feature.createform.formdetailsandbaseline.formdetails

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.cpu.quikdata.common.runOnIoThread
import com.cpu.quikdata.data.formdetails.FormDetails
import javax.inject.Inject

class FormDetailsViewModel @Inject constructor(
    private val mRepository: FormDetailsRepository
) : ViewModel() {

    val formDetails: LiveData<FormDetails>
        get() = mRepository.formDetails

    fun updateFormDetails(formDetails: FormDetails) =
        runOnIoThread { mRepository.updateData(formDetails) }

}
