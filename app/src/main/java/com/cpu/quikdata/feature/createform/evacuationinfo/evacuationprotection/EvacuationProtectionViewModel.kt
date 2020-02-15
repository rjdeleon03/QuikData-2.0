package com.cpu.quikdata.feature.createform.evacuationinfo.evacuationprotection

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.cpu.quikdata.common.runOnIoThread
import com.cpu.quikdata.data.evacuation.evacuationprotection.EvacuationProtection
import javax.inject.Inject

class EvacuationProtectionViewModel @Inject constructor(private val mRepository: EvacuationProtectionRepository)
    : ViewModel() {

    val evacuationProtection: LiveData<EvacuationProtection>
        get() = mRepository.evacuationProtection

    fun updateEvacuationProtection(evacuationProtection: EvacuationProtection) =
        runOnIoThread { mRepository.updateData(evacuationProtection) }
}