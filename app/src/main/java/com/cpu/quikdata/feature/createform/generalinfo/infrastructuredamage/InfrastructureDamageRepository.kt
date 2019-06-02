package com.cpu.quikdata.feature.createform.generalinfo.infrastructuredamage

import android.app.Application
import androidx.lifecycle.LiveData
import com.cpu.quikdata.base.BaseRepository
import com.cpu.quikdata.data.generalinfo.infrastructuredamage.InfrastructureDamageRow
import com.cpu.quikdata.utils.runOnIoThread

class InfrastructureDamageRepository(application: Application, formId: String) :
        BaseRepository<InfrastructureDamageRow>(application) {

    private val mInfrastructureDamage = mDatabase.infrastructureDamageRowDao().getByFormId(formId)

    val infrastructureDamage: LiveData<List<InfrastructureDamageRow>>
        get() = mInfrastructureDamage

    override fun updateData(data: InfrastructureDamageRow) {
        runOnIoThread {
            mDatabase.infrastructureDamageRowDao().update(data)
        }
    }

}