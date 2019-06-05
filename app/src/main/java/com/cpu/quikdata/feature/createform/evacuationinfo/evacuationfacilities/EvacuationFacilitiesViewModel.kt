package com.cpu.quikdata.feature.createform.evacuationinfo.evacuationfacilities

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.cpu.quikdata.data.evacuation.evacuationfacilities.EvacuationFacilities

class EvacuationFacilitiesViewModel(application: Application, evacuationId: String) :
    AndroidViewModel(application) {

    private val mRepository = EvacuationFacilitiesRepository(application, evacuationId)

    val evacuationFacilities: LiveData<EvacuationFacilities>
        get() = mRepository.evacuationFacilities

    fun updateEvacuationFacilities(evacuationFacilities: EvacuationFacilities) =
        mRepository.updateData(evacuationFacilities)
}