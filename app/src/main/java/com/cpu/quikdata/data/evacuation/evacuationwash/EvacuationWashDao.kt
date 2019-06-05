package com.cpu.quikdata.data.evacuation.evacuationwash

import androidx.lifecycle.LiveData
import androidx.room.*
import com.cpu.quikdata.base.BaseDao

@Dao
interface EvacuationWashDao : BaseDao<EvacuationWash> {

    @Query("SELECT * FROM evacuation_wash WHERE evacuationId = :evacuationId")
    fun getByEvacuationId(evacuationId: String): LiveData<EvacuationWash>
}