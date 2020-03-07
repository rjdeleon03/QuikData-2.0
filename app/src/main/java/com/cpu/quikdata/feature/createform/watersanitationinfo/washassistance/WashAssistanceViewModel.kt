package com.cpu.quikdata.feature.createform.watersanitationinfo.washassistance

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.cpu.quikdata.common.runOnIoThread
import com.cpu.quikdata.data.watersanitationinfo.washassistance.WashAssistanceRow
import javax.inject.Inject

class WashAssistanceViewModel @Inject constructor(private val mRepository: WashAssistanceRepository) :
    ViewModel() {

    val washAssistance: LiveData<List<WashAssistanceRow>>
        get() = mRepository.washAssistance

    fun updateRow(washAssistanceRow: WashAssistanceRow) =
        runOnIoThread { mRepository.updateData(washAssistanceRow) }

    fun createRow() =
        runOnIoThread { mRepository.createData() }

    fun deleteRow(row: WashAssistanceRow) =
        runOnIoThread { mRepository.deleteData(row) }
}
