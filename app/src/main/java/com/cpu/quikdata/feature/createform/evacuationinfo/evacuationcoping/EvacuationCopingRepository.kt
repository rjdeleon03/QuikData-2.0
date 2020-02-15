package com.cpu.quikdata.feature.createform.evacuationinfo.evacuationcoping

import androidx.lifecycle.LiveData
import com.cpu.quikdata.base.BaseUpdateableRepository
import com.cpu.quikdata.data.AppDatabase
import com.cpu.quikdata.data.evacuation.evacuationcoping.EvacuationCoping

class EvacuationCopingRepository(private val mDatabase: AppDatabase, val formId: String) :
    BaseUpdateableRepository<EvacuationCoping>() {

    private val mEvacuationCoping = mDatabase.evacuationCopingDao().getByEvacuationId(formId)

    val evacuationCoping: LiveData<EvacuationCoping>
        get() = mEvacuationCoping

    override suspend fun updateData(data: EvacuationCoping) {
        mEvacuationCoping.value?.let {
            it.copyFrom(data)
            mDatabase.evacuationCopingDao().update(it)
        }
    }
}