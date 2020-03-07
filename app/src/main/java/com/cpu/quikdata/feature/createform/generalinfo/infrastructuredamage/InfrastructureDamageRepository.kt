package com.cpu.quikdata.feature.createform.generalinfo.infrastructuredamage

import androidx.lifecycle.LiveData
import com.cpu.quikdata.data.AppDatabase
import com.cpu.quikdata.data.generalinfo.infrastructuredamage.InfrastructureDamageRow
import javax.inject.Inject

class InfrastructureDamageRepository @Inject constructor(
    private val mDatabase: AppDatabase, formId: String
) {

    private val mInfrastructureDamage = mDatabase.infrastructureDamageRowDao().getByFormId(formId)

    val infrastructureDamage: LiveData<List<InfrastructureDamageRow>>
        get() = mInfrastructureDamage

    suspend fun updateData(data: InfrastructureDamageRow) {
        mDatabase.infrastructureDamageRowDao().update(data)
    }

}