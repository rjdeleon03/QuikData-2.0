package com.cpu.quikdata.feature.createform.shelterinfo.sheltercoping

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.cpu.quikdata.data.shelterinfo.sheltercoping.ShelterCoping
import javax.inject.Inject


class ShelterCopingViewModel @Inject constructor(private val mRepository: ShelterCopingRepository)
    : ViewModel() {

    val shelterCoping: LiveData<ShelterCoping>
        get() = mRepository.shelterCoping

    fun updateShelterCoping(shelterCoping: ShelterCoping) =
        mRepository.updateData(shelterCoping)
}
