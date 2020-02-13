package com.cpu.quikdata.feature.createform.evacuationinfo.evacuationage

import androidx.lifecycle.LiveData
import com.cpu.quikdata.base.BaseUpdateableRepository
import com.cpu.quikdata.data.AppDatabase
import com.cpu.quikdata.data.evacuation.evacuationagerow.EvacuationAgeRow
import com.cpu.quikdata.utils.runOnIoThread

class EvacuationAgeRepository(private val mDatabase: AppDatabase, val formId: String) :
    BaseUpdateableRepository<EvacuationAgeRow>() {

    private val mEvacuationAge = mDatabase.evacuationAgeRowDao().getByEvacuationId(formId)

    val evacuationAge: LiveData<List<EvacuationAgeRow>>
        get() = mEvacuationAge

    override fun updateData(data: EvacuationAgeRow) {
        runOnIoThread {
            mDatabase.evacuationAgeRowDao().update(data)
        }
    }
}