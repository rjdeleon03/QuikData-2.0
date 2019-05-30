package com.cpu.quikdata.feature.createform.generalinfo.infrastructuredamage

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.cpu.quikdata.data.generalinfo.infrastructuredamage.InfrastructureDamageRow

class InfrastructureDamageViewModel(application: Application, formId: String) :
    AndroidViewModel(application) {

    private val mRepository = InfrastructureDamageRepository(application, formId)

    val infrastructureDamage: LiveData<List<InfrastructureDamageRow>>
        get() = mRepository.infrastructureDamage

    fun updateRow(infrastructureDamageRow: InfrastructureDamageRow) =
        mRepository.updateData(infrastructureDamageRow)

}
