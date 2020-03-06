package com.cpu.quikdata.feature.createform.watersanitationinfo.washassistance

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.cpu.quikdata.data.watersanitationinfo.washassistance.WashAssistanceRow
import javax.inject.Inject

class WashAssistanceViewModel @Inject constructor(private val mRepository: WashAssistanceRepository)
    : ViewModel() {

    val washAssistance: LiveData<List<WashAssistanceRow>>
        get() = mRepository.washAssistance

    fun updateRow(washAssistanceRow: WashAssistanceRow) = mRepository.updateData(washAssistanceRow)

    fun createRow() = mRepository.createData()

    fun deleteRow(row: WashAssistanceRow) = mRepository.deleteData(row)
}
