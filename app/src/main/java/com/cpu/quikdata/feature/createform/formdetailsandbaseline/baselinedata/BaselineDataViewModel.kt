package com.cpu.quikdata.feature.createform.formdetailsandbaseline.baselinedata

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.cpu.quikdata.common.runOnIoThread
import com.cpu.quikdata.data.baselinedata.BaselineData
import com.cpu.quikdata.data.prefilleddata.PrefilledData
import javax.inject.Inject

class BaselineDataViewModel @Inject constructor (private val mRepository: BaselineDataRepository)
    : ViewModel() {

    val baselineData : LiveData<BaselineData>
        get() = mRepository.baselineData

    val prefilledData: LiveData<PrefilledData>
        get() = mRepository.prefilledData

    fun updateBaselineData(baselineData: BaselineData) {
        runOnIoThread { mRepository.updateData(baselineData) }
    }

    fun pullPrefilledData() = mRepository.pullPrefilledData()

}
