package com.cpu.quikdata.data.evacuation.evacuationfacilities

import androidx.lifecycle.LiveData
import androidx.room.*
import com.cpu.quikdata.base.BaseDao

@Dao
interface EvacuationFacilitiesDao : BaseDao<EvacuationFacilities> {

    @Query("SELECT * FROM evacuation_facilities WHERE evacuationId = :evacuationId")
    fun getByEvacuationId(evacuationId: String): LiveData<EvacuationFacilities>
}