package com.cpu.quikdata.feature.createform.shelterinfo.sheltergaps

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.cpu.quikdata.data.shelterinfo.sheltergaps.ShelterGaps
import javax.inject.Inject

class ShelterGapsViewModel @Inject constructor(private val mRepository: ShelterGapsRepository)
    : ViewModel() {

    val shelterGaps: LiveData<ShelterGaps>
        get() = mRepository.shelterGaps

    fun updateShelterGaps(shelterGaps: ShelterGaps) =
        mRepository.updateData(shelterGaps)
}
