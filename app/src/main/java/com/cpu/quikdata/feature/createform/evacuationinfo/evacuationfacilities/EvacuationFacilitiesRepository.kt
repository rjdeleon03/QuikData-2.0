package com.cpu.quikdata.feature.createform.evacuationinfo.evacuationfacilities

import androidx.lifecycle.LiveData
import com.cpu.quikdata.base.BaseUpdateableRepository
import com.cpu.quikdata.data.AppDatabase
import com.cpu.quikdata.data.evacuation.evacuationfacilities.EvacuationFacilities
import com.cpu.quikdata.utils.runOnIoThread

class EvacuationFacilitiesRepository(private val mDatabase: AppDatabase, val formId: String) :
    BaseUpdateableRepository<EvacuationFacilities>() {

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