package com.cpu.quikdata.feature.createform.evacuationinfo.evacuationwash

import androidx.lifecycle.LiveData
import com.cpu.quikdata.base.BaseUpdateableRepository
import com.cpu.quikdata.data.AppDatabase
import com.cpu.quikdata.data.evacuation.evacuationwash.EvacuationWash
import com.cpu.quikdata.utils.runOnIoThread

class EvacuationWashRepository(private val mDatabase: AppDatabase, val formId: String) :
    BaseUpdateableRepository<EvacuationWash>() {

    private val mEvacuationWash = mDatabase.evacuationWashDao().getByEvacuationId(formId)

    val evacuationWash: LiveData<EvacuationWash>
        get() = mEvacuationWash

    override fun updateData(data: EvacuationWash) {
        runOnIoThread {
            val oldEvacuationWash = mEvacuationWash.value!!
            oldEvacuationWash.copyFrom(data)
            mDatabase.evacuationWashDao().update(oldEvacuationWash)
        }
    }
}