package com.cpu.quikdata.feature.createform.generalinfo.infrastructuredamage

import androidx.lifecycle.LiveData
import com.cpu.quikdata.data.AppDatabase
import com.cpu.quikdata.data.generalinfo.infrastructuredamage.InfrastructureDamageRow
import com.cpu.quikdata.utils.runOnIoThread
import javax.inject.Inject

class InfrastructureDamageRepository @Inject constructor(
    private val mDatabase: AppDatabase, private val mFormId: String) {

    private val mInfrastructureDamage = mDatabase.infrastructureDamageRowDao().getByFormId(mFormId)

    val infrastructureDamage: LiveData<List<InfrastructureDamageRow>>
        get() = mInfrastructureDamage

    fun updateData(data: InfrastructureDamageRow) {
        runOnIoThread {
            mDatabase.infrastructureDamageRowDao().update(data)
        }
    }

}