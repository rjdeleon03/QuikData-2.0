package com.cpu.quikdata.feature.createform.generalinfo.infrastructuredamage

import androidx.lifecycle.LiveData
import com.cpu.quikdata.base.BaseUpdateableRepository
import com.cpu.quikdata.data.AppDatabase
import com.cpu.quikdata.data.generalinfo.infrastructuredamage.InfrastructureDamageRow
import com.cpu.quikdata.utils.runOnIoThread

class InfrastructureDamageRepository(private val mDatabase: AppDatabase, val formId: String) :
        BaseUpdateableRepository<InfrastructureDamageRow>() {

    private val mInfrastructureDamage = mDatabase.infrastructureDamageRowDao().getByFormId(formId)

    val infrastructureDamage: LiveData<List<InfrastructureDamageRow>>
        get() = mInfrastructureDamage

    override fun updateData(data: InfrastructureDamageRow) {
        runOnIoThread {
            mDatabase.infrastructureDamageRowDao().update(data)
        }
    }

}