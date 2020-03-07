package com.cpu.quikdata.feature.createform.evacuationinfo.evacuationfacilities

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.cpu.quikdata.common.runOnIoThread
import com.cpu.quikdata.data.evacuation.evacuationfacilities.EvacuationFacilities
import javax.inject.Inject

class EvacuationFacilitiesViewModel @Inject constructor(private val mRepository: EvacuationFacilitiesRepository)
    : ViewModel() {

    val evacuationFacilities: LiveData<EvacuationFacilities>
        get() = mRepository.evacuationFacilities

    fun updateEvacuationFacilities(evacuationFacilities: EvacuationFacilities) =
        runOnIoThread { mRepository.updateData(evacuationFacilities) }
}