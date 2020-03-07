package com.cpu.quikdata.feature.createform.shelterinfo.shelterassistance

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.cpu.quikdata.common.runOnIoThread
import com.cpu.quikdata.data.shelterinfo.shelterassistance.ShelterAssistanceRow
import javax.inject.Inject

class ShelterAssistanceViewModel @Inject constructor(private val mRepository: ShelterAssistanceRepository)
    : ViewModel() {

    val shelterAssistance: LiveData<List<ShelterAssistanceRow>>
        get() = mRepository.shelterAssistance

    fun updateRow(shelterAssistanceRow: ShelterAssistanceRow) =
        runOnIoThread { mRepository.updateData(shelterAssistanceRow) }

    fun createRow() =
        runOnIoThread { mRepository.createData() }

    fun deleteRow(row: ShelterAssistanceRow) =
        runOnIoThread { mRepository.deleteData(row) }
}
