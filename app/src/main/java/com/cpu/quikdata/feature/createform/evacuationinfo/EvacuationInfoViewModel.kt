package com.cpu.quikdata.feature.createform.evacuationinfo

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.cpu.quikdata.data.evacuation.EvacuationItemDetails

class EvacuationInfoViewModel(application: Application, formId: String) : AndroidViewModel(application) {

    private val mFormId = formId
    private val mRepository = EvacuationInfoRepository(application, mFormId)

    val evacuationInfos: LiveData<List<EvacuationItemDetails>>
        get() = mRepository.evacuationInfo

    fun createEvacuationInfo(id: String) = mRepository.createData(id)

    fun deleteEvacuationInfo(item: EvacuationItemDetails) = mRepository.deleteData(item)
}
