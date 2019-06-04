package com.cpu.quikdata.feature.createform.evacuationinfo

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.cpu.quikdata.data.evacuation.EvacuationItem

class EvacuationInfoViewModel(application: Application, formId: String) : AndroidViewModel(application) {

    private val mFormId = formId
    private val mRepository = EvacuationInfoRepository(application, mFormId)

    val evacuationInfos: LiveData<List<EvacuationItem>>
        get() = mRepository.evacuationInfos

    fun createEvacuationInfo() = mRepository.createData()
}
