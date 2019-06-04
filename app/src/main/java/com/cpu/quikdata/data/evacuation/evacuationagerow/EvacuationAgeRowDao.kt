package com.cpu.quikdata.data.evacuation.evacuationagerow

import androidx.lifecycle.LiveData
import androidx.room.*
import com.cpu.quikdata.base.BaseDao

@Dao
interface EvacuationAgeRowDao : BaseDao<EvacuationAgeRow> {

    @Query("SELECT * FROM evacuation_age_row WHERE evacuationId = :evacuationId ORDER BY type")
    fun getByEvacuationId(evacuationId: String): LiveData<List<EvacuationAgeRow>>
}