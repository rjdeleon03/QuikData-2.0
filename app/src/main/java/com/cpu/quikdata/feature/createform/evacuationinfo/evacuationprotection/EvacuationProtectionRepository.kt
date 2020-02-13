package com.cpu.quikdata.feature.createform.evacuationinfo.evacuationprotection

import androidx.lifecycle.LiveData
import com.cpu.quikdata.base.BaseUpdateableRepository
import com.cpu.quikdata.data.AppDatabase
import com.cpu.quikdata.data.evacuation.evacuationprotection.EvacuationProtection
import com.cpu.quikdata.utils.runOnIoThread

class EvacuationProtectionRepository(private val mDatabase: AppDatabase, val formId: String) :
    BaseUpdateableRepository<EvacuationProtection>() {

    private val mEvacuationProtection = mDatabase.evacuationProtectionDao().getByEvacuationId(formId)

    val evacuationProtection: LiveData<EvacuationProtection>
        get() = mEvacuationProtection

    override fun updateData(data: EvacuationProtection) {
        runOnIoThread {
            val oldEvacuationProtection = mEvacuationProtection.value!!
            oldEvacuationProtection.copyFrom(data)
            mDatabase.evacuationProtectionDao().update(oldEvacuationProtection)
        }
    }
}