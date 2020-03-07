package com.cpu.quikdata.feature.createform.evacuationinfo.evacuationcoping

import androidx.lifecycle.LiveData
import com.cpu.quikdata.data.AppDatabase
import com.cpu.quikdata.data.evacuation.evacuationcoping.EvacuationCoping
import com.cpu.quikdata.di.EvacuationId
import com.cpu.quikdata.utils.runOnIoThread
import javax.inject.Inject

class EvacuationCopingRepository @Inject constructor(private val mDatabase: AppDatabase,
                                                     @EvacuationId evacuationId: String) {

    private val mEvacuationCoping = mDatabase.evacuationCopingDao().getByEvacuationId(evacuationId)

    val evacuationCoping: LiveData<EvacuationCoping>
        get() = mEvacuationCoping

    fun updateData(data: EvacuationCoping) {
        runOnIoThread {
            val oldEvacuationCoping = mEvacuationCoping.value!!
            oldEvacuationCoping.copyFrom(data)
            mDatabase.evacuationCopingDao().update(oldEvacuationCoping)
        }
    }
}