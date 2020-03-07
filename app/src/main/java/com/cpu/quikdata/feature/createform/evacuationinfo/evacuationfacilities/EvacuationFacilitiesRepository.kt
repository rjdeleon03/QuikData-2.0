package com.cpu.quikdata.feature.createform.evacuationinfo.evacuationfacilities

import androidx.lifecycle.LiveData
import com.cpu.quikdata.data.AppDatabase
import com.cpu.quikdata.data.evacuation.evacuationfacilities.EvacuationFacilities
import com.cpu.quikdata.di.EvacuationId
import javax.inject.Inject

class EvacuationFacilitiesRepository @Inject constructor(
    private val mDatabase: AppDatabase,
    @EvacuationId evacuationId: String
) {

    private val mEvacuationFacilities =
        mDatabase.evacuationFacilitiesDao().getByEvacuationId(evacuationId)

    val evacuationFacilities: LiveData<EvacuationFacilities>
        get() = mEvacuationFacilities

    suspend fun updateData(data: EvacuationFacilities) {
        mEvacuationFacilities.value?.apply {
            copyFrom(data)
            mDatabase.evacuationFacilitiesDao().update(this)
        }
    }
}