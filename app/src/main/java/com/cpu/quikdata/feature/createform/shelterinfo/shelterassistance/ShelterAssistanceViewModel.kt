package com.cpu.quikdata.feature.createform.shelterinfo.shelterassistance

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.cpu.quikdata.data.shelterinfo.shelterassistance.ShelterAssistanceRow

class ShelterAssistanceViewModel(application: Application, formId: String) :
    AndroidViewModel(application) {

    private val mRepository = ShelterAssistanceRepository(application, formId)

    val shelterAssistance: LiveData<List<ShelterAssistanceRow>>
        get() = mRepository.shelterAssistance

    fun updateRow(shelterAssistanceRow: ShelterAssistanceRow) = mRepository.updateData(shelterAssistanceRow)

    fun createRow() = mRepository.createData()

    fun deleteRow(row: ShelterAssistanceRow) = mRepository.deleteData(row)
}
