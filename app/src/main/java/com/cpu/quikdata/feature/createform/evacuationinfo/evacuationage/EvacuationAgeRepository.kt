package com.cpu.quikdata.feature.createform.evacuationinfo.evacuationage

import android.app.Application
import androidx.lifecycle.LiveData
import com.cpu.quikdata.base.BaseRepository
import com.cpu.quikdata.data.evacuation.evacuationagerow.EvacuationAgeRow
import com.cpu.quikdata.utils.runOnIoThread

class EvacuationAgeRepository(application: Application, formId: String) :
    BaseRepository<EvacuationAgeRow>(application) {

    private val mEvacuationAge = mDatabase.evacuationAgeRowDao().getByEvacuationId(formId)

    val evacuationAge: LiveData<List<EvacuationAgeRow>>
        get() = mEvacuationAge

    override fun updateData(data: EvacuationAgeRow) {
        runOnIoThread {
            mDatabase.evacuationAgeRowDao().update(data)
        }
    }
}