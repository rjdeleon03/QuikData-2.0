package com.cpu.quikdata.feature.createform.healthinfo.healthgaps

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.cpu.quikdata.common.runOnIoThread
import com.cpu.quikdata.data.health.healthgaps.HealthGaps
import javax.inject.Inject

class HealthGapsViewModel @Inject constructor(private val mRepository: HealthGapsRepository) :
    ViewModel() {

    val healthGaps: LiveData<HealthGaps>
        get() = mRepository.healthGaps

    fun updateHealthGaps(healthGaps: HealthGaps) =
        runOnIoThread { mRepository.updateData(healthGaps) }
}
