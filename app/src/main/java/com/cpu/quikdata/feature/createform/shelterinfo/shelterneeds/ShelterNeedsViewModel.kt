package com.cpu.quikdata.feature.createform.shelterinfo.shelterneeds

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.cpu.quikdata.common.runOnIoThread
import com.cpu.quikdata.data.shelterinfo.shelterneedsrow.ShelterNeedsRow
import javax.inject.Inject

class ShelterNeedsViewModel @Inject constructor(private val mRepository: ShelterNeedsRepository)
    : ViewModel() {

    val shelterNeeds: LiveData<List<ShelterNeedsRow>>
        get() = mRepository.shelterNeeds

    fun updateRow(shelterNeedsRow: ShelterNeedsRow) =
        runOnIoThread { mRepository.updateData(shelterNeedsRow) }

}
