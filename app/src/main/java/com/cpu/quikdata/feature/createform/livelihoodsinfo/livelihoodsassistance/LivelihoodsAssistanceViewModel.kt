package com.cpu.quikdata.feature.createform.livelihoodsinfo.livelihoodsassistance

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.cpu.quikdata.data.livelihoodsinfo.livelihoodsassistance.LivelihoodsAssistanceRow

class LivelihoodsAssistanceViewModel(application: Application, formId: String) :
    AndroidViewModel(application) {

    private val mRepository = LivelihoodsAssistanceRepository(application, formId)

    val livelihoodsAssistance: LiveData<List<LivelihoodsAssistanceRow>>
        get() = mRepository.livelihoodsAssistance

    fun updateRow(livelihoodsAssistanceRow: LivelihoodsAssistanceRow) = mRepository.updateData(livelihoodsAssistanceRow)

    fun createRow() = mRepository.createData()

    fun deleteRow(row: LivelihoodsAssistanceRow) = mRepository.deleteData(row)
}
