package com.cpu.quikdata.feature.createform.livelihoodsinfo.livelihoodsassistance

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.cpu.quikdata.data.livelihoodsinfo.livelihoodsassistance.LivelihoodsAssistanceRow
import javax.inject.Inject

class LivelihoodsAssistanceViewModel @Inject constructor(private val mRepository: LivelihoodsAssistanceRepository)
    : ViewModel() {

    val livelihoodsAssistance: LiveData<List<LivelihoodsAssistanceRow>>
        get() = mRepository.livelihoodsAssistance

    fun updateRow(livelihoodsAssistanceRow: LivelihoodsAssistanceRow) = mRepository.updateData(livelihoodsAssistanceRow)

    fun createRow() = mRepository.createData()

    fun deleteRow(row: LivelihoodsAssistanceRow) = mRepository.deleteData(row)
}
