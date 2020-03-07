package com.cpu.quikdata.feature.createform.evacuationinfo.evacuationcoping

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.cpu.quikdata.common.runOnIoThread
import com.cpu.quikdata.data.evacuation.evacuationcoping.EvacuationCoping
import javax.inject.Inject

class EvacuationCopingViewModel @Inject constructor(private val mRepository: EvacuationCopingRepository)
    : ViewModel() {

    val evacuationCoping: LiveData<EvacuationCoping>
        get() = mRepository.evacuationCoping

    fun updateEvacuationCoping(evacuationCoping: EvacuationCoping) =
        runOnIoThread { mRepository.updateData(evacuationCoping) }
}

