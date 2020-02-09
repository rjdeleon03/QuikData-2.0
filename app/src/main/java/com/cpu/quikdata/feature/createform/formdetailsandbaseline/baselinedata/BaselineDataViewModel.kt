package com.cpu.quikdata.feature.createform.formdetailsandbaseline.baselinedata

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.cpu.quikdata.data.baselinedata.BaselineData
import com.cpu.quikdata.data.prefilleddata.PrefilledData
import com.cpu.quikdata.feature.QuikDataApp

class BaselineDataViewModel(application: Application, formId: String) : AndroidViewModel(application) {

    private val mRepository = BaselineDataRepository(application as QuikDataApp, formId)

    val baselineData : LiveData<BaselineData>
        get() = mRepository.baselineData

    val prefilledData: LiveData<PrefilledData>
        get() = mRepository.prefilledData

    fun updateBaselineData(baselineData: BaselineData) = mRepository.updateData(baselineData)

    fun pullPrefilledData() = mRepository.pullPrefilledData()

}
