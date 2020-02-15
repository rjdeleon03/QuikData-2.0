package com.cpu.quikdata.feature.createform.evacuationinfo

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.cpu.quikdata.common.runOnIoThread
import com.cpu.quikdata.data.evacuation.EvacuationItemDetails
import javax.inject.Inject

class EvacuationInfoViewModel @Inject constructor(private val mRepository: EvacuationInfoRepository)
    : ViewModel() {

    val evacuationInfos: LiveData<List<EvacuationItemDetails>>
        get() = mRepository.evacuationInfo

    fun createEvacuationInfo(id: String) =
        runOnIoThread { mRepository.createData(id) }

    fun deleteEvacuationInfo(item: EvacuationItemDetails) =
        runOnIoThread { mRepository.deleteData(item) }
}
