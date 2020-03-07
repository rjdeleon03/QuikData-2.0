package com.cpu.quikdata.feature.createform.evacuationinfo.evacuationcoping

import androidx.lifecycle.LiveData
import com.cpu.quikdata.data.AppDatabase
import com.cpu.quikdata.data.evacuation.evacuationcoping.EvacuationCoping
import com.cpu.quikdata.di.EvacuationId
import javax.inject.Inject

class EvacuationCopingRepository @Inject constructor(private val mDatabase: AppDatabase,
                                                     @EvacuationId evacuationId: String) {

    private val mEvacuationCoping = mDatabase.evacuationCopingDao().getByEvacuationId(evacuationId)

    val evacuationCoping: LiveData<EvacuationCoping>
        get() = mEvacuationCoping

    suspend fun updateData(data: EvacuationCoping) {
        mEvacuationCoping.value?.apply {
            copyFrom(data)
            mDatabase.evacuationCopingDao().update(this)
        }
    }
}