package com.cpu.quikdata.feature.createform.generalinfo.infrastructuredamage

import androidx.lifecycle.LiveData
import com.cpu.quikdata.base.BaseUpdateableRepository
import com.cpu.quikdata.data.AppDatabase
import com.cpu.quikdata.data.generalinfo.infrastructuredamage.InfrastructureDamageRow

class InfrastructureDamageRepository(private val mDatabase: AppDatabase, val formId: String) :
        BaseUpdateableRepository<InfrastructureDamageRow>() {

    private val mInfrastructureDamage = mDatabase.infrastructureDamageRowDao().getByFormId(formId)

    val infrastructureDamage: LiveData<List<InfrastructureDamageRow>>
        get() = mInfrastructureDamage

    override suspend fun updateData(data: InfrastructureDamageRow) {
        mDatabase.infrastructureDamageRowDao().update(data)
    }

}