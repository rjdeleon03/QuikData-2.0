package com.cpu.quikdata.feature.createform.evacuationinfo.evacuationprotection

import androidx.lifecycle.LiveData
import com.cpu.quikdata.base.BaseUpdateableRepository
import com.cpu.quikdata.data.AppDatabase
import com.cpu.quikdata.data.evacuation.evacuationprotection.EvacuationProtection

class EvacuationProtectionRepository(private val mDatabase: AppDatabase, val formId: String) :
    BaseUpdateableRepository<EvacuationProtection>() {

    private val mEvacuationProtection = mDatabase.evacuationProtectionDao().getByEvacuationId(formId)

    val evacuationProtection: LiveData<EvacuationProtection>
        get() = mEvacuationProtection

    override suspend fun updateData(data: EvacuationProtection) {
        mEvacuationProtection.value?.let {
            it.copyFrom(data)
            mDatabase.evacuationProtectionDao().update(it)
        }
    }
}