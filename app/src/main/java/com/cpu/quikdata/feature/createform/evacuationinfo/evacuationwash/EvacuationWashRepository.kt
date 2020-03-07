package com.cpu.quikdata.feature.createform.evacuationinfo.evacuationwash

import androidx.lifecycle.LiveData
import com.cpu.quikdata.data.AppDatabase
import com.cpu.quikdata.data.evacuation.evacuationwash.EvacuationWash
import com.cpu.quikdata.di.EvacuationId
import com.cpu.quikdata.utils.runOnIoThread
import javax.inject.Inject

class EvacuationWashRepository @Inject constructor(private val mDatabase: AppDatabase,
                                                   @EvacuationId evacuationId: String) {

    private val mEvacuationWash = mDatabase.evacuationWashDao().getByEvacuationId(evacuationId)

    val evacuationWash: LiveData<EvacuationWash>
        get() = mEvacuationWash

    fun updateData(data: EvacuationWash) {
        runOnIoThread {
            val oldEvacuationWash = mEvacuationWash.value!!
            oldEvacuationWash.copyFrom(data)
            mDatabase.evacuationWashDao().update(oldEvacuationWash)
        }
    }
}