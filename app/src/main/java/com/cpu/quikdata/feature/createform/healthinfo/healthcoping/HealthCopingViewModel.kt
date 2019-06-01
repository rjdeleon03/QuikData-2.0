package com.cpu.quikdata.feature.createform.healthinfo.healthcoping

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.cpu.quikdata.data.health.healthcoping.HealthCoping

class HealthCopingViewModel(application: Application, formId: String) :
    AndroidViewModel(application) {

    private val mRepository = HealthCopingRepository(application, formId)

    val healthCoping : LiveData<HealthCoping>
        get() = mRepository.healthCoping

    fun updateHealthCoping(healthCoping: HealthCoping) =
        mRepository.updateData(healthCoping)
}
