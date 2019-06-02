package com.cpu.quikdata.feature.createform.watersanitationinfo.washassistance

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.cpu.quikdata.data.watersanitationinfo.washassistance.WashAssistanceRow

class WashAssistanceViewModel(application: Application, formId: String) :
    AndroidViewModel(application) {

    private val mRepository = WashAssistanceRepository(application, formId)

    val washAssistance: LiveData<List<WashAssistanceRow>>
        get() = mRepository.washAssistance

    fun updateRow(washAssistanceRow: WashAssistanceRow) = mRepository.updateData(washAssistanceRow)

    fun createRow() = mRepository.createData()
}
