package com.cpu.quikdata.feature.createform.healthinfo.healthcoping

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.cpu.quikdata.data.health.healthcoping.HealthCoping
import javax.inject.Inject

class HealthCopingViewModel @Inject constructor(private val mRepository: HealthCopingRepository)
    : ViewModel() {

    val healthCoping : LiveData<HealthCoping>
        get() = mRepository.healthCoping

    fun updateHealthCoping(healthCoping: HealthCoping) =
        mRepository.updateData(healthCoping)
}
