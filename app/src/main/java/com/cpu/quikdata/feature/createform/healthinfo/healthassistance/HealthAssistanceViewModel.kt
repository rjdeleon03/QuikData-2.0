package com.cpu.quikdata.feature.createform.healthinfo.healthassistance

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.cpu.quikdata.data.health.healthassistance.HealthAssistanceRow

class HealthAssistanceViewModel(application: Application, formId: String) :
    AndroidViewModel(application) {

    private val mRepository = HealthAssistanceRepository(application, formId)

    val healthAssistance: LiveData<List<HealthAssistanceRow>>
        get() = mRepository.healthAssistance

    fun updateRow(healthAssistanceRow: HealthAssistanceRow) = mRepository.updateData(healthAssistanceRow)

    fun createRow() = mRepository.createData()

    fun deleteRow(row: HealthAssistanceRow) = mRepository.deleteData(row)
}
