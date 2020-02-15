package com.cpu.quikdata.feature.createform.evacuationinfo.evacuationage

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.cpu.quikdata.common.runOnIoThread
import com.cpu.quikdata.data.evacuation.evacuationagerow.EvacuationAgeRow
import javax.inject.Inject

class EvacuationAgeViewModel @Inject constructor (private val mRepository: EvacuationAgeRepository)
    : ViewModel() {

    val evacuationAge: LiveData<List<EvacuationAgeRow>>
        get() = mRepository.evacuationAge

    fun updateRow(evacuationAgeRow: EvacuationAgeRow) =
        runOnIoThread { mRepository.updateData(evacuationAgeRow) }
}
