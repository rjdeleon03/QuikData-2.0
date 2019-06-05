package com.cpu.quikdata.feature.createform.evacuationinfo.evacuationfacilities

import android.app.Application
import androidx.lifecycle.LiveData
import com.cpu.quikdata.base.BaseRepository
import com.cpu.quikdata.data.evacuation.evacuationfacilities.EvacuationFacilities
import com.cpu.quikdata.utils.runOnIoThread

class EvacuationFacilitiesRepository(application: Application, formId: String) :
    BaseRepository<EvacuationFacilities>(application) {

    private val mEvacuationFacilities = mDatabase.evacuationFacilitiesDao().getByEvacuationId(formId)

    val evacuationFacilities: LiveData<EvacuationFacilities>
        get() = mEvacuationFacilities

    override fun updateData(data: EvacuationFacilities) {
        runOnIoThread {
            val oldEvacuationFacilities = mEvacuationFacilities.value!!
            oldEvacuationFacilities.copyFrom(data)
            mDatabase.evacuationFacilitiesDao().update(oldEvacuationFacilities)
        }
    }
}