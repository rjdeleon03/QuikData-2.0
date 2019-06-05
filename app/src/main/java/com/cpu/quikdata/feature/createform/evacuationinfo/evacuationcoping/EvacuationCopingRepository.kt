package com.cpu.quikdata.feature.createform.evacuationinfo.evacuationcoping

import android.app.Application
import androidx.lifecycle.LiveData
import com.cpu.quikdata.base.BaseRepository
import com.cpu.quikdata.data.evacuation.evacuationcoping.EvacuationCoping
import com.cpu.quikdata.utils.runOnIoThread

class EvacuationCopingRepository(application: Application, formId: String) :
    BaseRepository<EvacuationCoping>(application) {

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