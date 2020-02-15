package com.cpu.quikdata.feature.createform.evacuationinfo.evacuationwash

import androidx.lifecycle.LiveData
import com.cpu.quikdata.base.BaseUpdateableRepository
import com.cpu.quikdata.data.AppDatabase
import com.cpu.quikdata.data.evacuation.evacuationwash.EvacuationWash

class EvacuationWashRepository(private val mDatabase: AppDatabase, val formId: String) :
    BaseUpdateableRepository<EvacuationWash>() {

    private val mEvacuationWash = mDatabase.evacuationWashDao().getByEvacuationId(formId)

    val evacuationWash: LiveData<EvacuationWash>
        get() = mEvacuationWash

    override suspend fun updateData(data: EvacuationWash) {
        mEvacuationWash.value?.let {
            it.copyFrom(data)
            mDatabase.evacuationWashDao().update(it)
        }
    }
}