package com.cpu.quikdata.feature.createform.healthinfo.healthgaps

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.cpu.quikdata.data.health.healthgaps.HealthGaps

class HealthGapsViewModel(application: Application, formId: String) :
    AndroidViewModel(application) {

    private val mRepository = HealthGapsRepository(application, formId)

    val healthGaps : LiveData<HealthGaps>
        get() = mRepository.healthGaps

    fun updateHealthGaps(healthGaps: HealthGaps) =
        mRepository.updateData(healthGaps)
}
