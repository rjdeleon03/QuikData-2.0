package com.cpu.quikdata.feature.createform.evacuationinfo.evacuationage

import androidx.lifecycle.LiveData
import com.cpu.quikdata.data.AppDatabase
import com.cpu.quikdata.data.evacuation.evacuationagerow.EvacuationAgeRow
import com.cpu.quikdata.di.EvacuationId
import com.cpu.quikdata.utils.runOnIoThread
import javax.inject.Inject

class EvacuationAgeRepository @Inject constructor(private val mDatabase: AppDatabase,
                                                  @EvacuationId evacuationId: String) {

    private val mEvacuationAge = mDatabase.evacuationAgeRowDao().getByEvacuationId(evacuationId)

    val evacuationAge: LiveData<List<EvacuationAgeRow>>
        get() = mEvacuationAge

    fun updateData(data: EvacuationAgeRow) {
        runOnIoThread {
            mDatabase.evacuationAgeRowDao().update(data)
        }
    }
}