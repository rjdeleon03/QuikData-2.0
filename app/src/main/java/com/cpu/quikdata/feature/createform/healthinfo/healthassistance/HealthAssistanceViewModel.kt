package com.cpu.quikdata.feature.createform.healthinfo.healthassistance

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.cpu.quikdata.common.runOnIoThread
import com.cpu.quikdata.data.health.healthassistance.HealthAssistanceRow
import javax.inject.Inject

class HealthAssistanceViewModel @Inject constructor(private val mRepository: HealthAssistanceRepository) :
    ViewModel() {

    val healthAssistance: LiveData<List<HealthAssistanceRow>>
        get() = mRepository.healthAssistance

    fun updateRow(healthAssistanceRow: HealthAssistanceRow) =
        runOnIoThread { mRepository.updateData(healthAssistanceRow) }

    fun createRow() =
        runOnIoThread { mRepository.createData() }

    fun deleteRow(row: HealthAssistanceRow) =
        runOnIoThread { mRepository.deleteData(row) }
}
