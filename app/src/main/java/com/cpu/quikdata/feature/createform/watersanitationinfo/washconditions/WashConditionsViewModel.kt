package com.cpu.quikdata.feature.createform.watersanitationinfo.washconditions

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.cpu.quikdata.data.watersanitationinfo.washconditions.WashConditions
import javax.inject.Inject

class WashConditionsViewModel @Inject constructor(private val mRepository: WashConditionsRepository)
    : ViewModel() {

    val washConditions : LiveData<WashConditions>
        get() = mRepository.washConditions

    fun updateWashConditions(washConditions: WashConditions) =
        mRepository.updateData(washConditions)
}
