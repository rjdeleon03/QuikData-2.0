package com.cpu.quikdata.feature.createform.evacuationinfo.evacuationcoping

import androidx.lifecycle.LiveData
import com.cpu.quikdata.base.BaseUpdateableRepository
import com.cpu.quikdata.data.AppDatabase
import com.cpu.quikdata.data.evacuation.evacuationcoping.EvacuationCoping
import com.cpu.quikdata.utils.runOnIoThread

class EvacuationCopingRepository(private val mDatabase: AppDatabase, val formId: String) :
    BaseUpdateableRepository<EvacuationCoping>() {

    private val mEvacuationCoping = mDatabase.evacuationCopingDao().getByEvacuationId(formId)

    val evacuationCoping: LiveData<EvacuationCoping>
        get() = mEvacuationCoping

    override fun updateData(data: EvacuationCoping) {
        runOnIoThread {
            val oldEvacuationCoping = mEvacuationCoping.value!!
            oldEvacuationCoping.copyFrom(data)
            mDatabase.evacuationCopingDao().update(oldEvacuationCoping)
        }
    }
}