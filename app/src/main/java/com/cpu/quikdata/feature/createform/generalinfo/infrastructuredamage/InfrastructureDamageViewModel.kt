package com.cpu.quikdata.feature.createform.generalinfo.infrastructuredamage

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.cpu.quikdata.common.runOnIoThread
import com.cpu.quikdata.data.generalinfo.infrastructuredamage.InfrastructureDamageRow
import javax.inject.Inject

class InfrastructureDamageViewModel @Inject constructor(private val mRepository: InfrastructureDamageRepository) :
    ViewModel() {

    val infrastructureDamage: LiveData<List<InfrastructureDamageRow>>
        get() = mRepository.infrastructureDamage

    fun updateRow(infrastructureDamageRow: InfrastructureDamageRow) =
        runOnIoThread { mRepository.updateData(infrastructureDamageRow) }

}
