package com.cpu.quikdata.feature.createform.evacuationinfo.evacuationwash

import android.app.Application
import androidx.lifecycle.LiveData
import com.cpu.quikdata.base.BaseRepository
import com.cpu.quikdata.data.evacuation.evacuationwash.EvacuationWash
import com.cpu.quikdata.utils.runOnIoThread

class EvacuationWashRepository(application: Application, formId: String) :
    BaseRepository<EvacuationWash>(application) {

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