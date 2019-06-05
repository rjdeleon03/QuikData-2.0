package com.cpu.quikdata.data.evacuation.evacuationcoping

import androidx.lifecycle.LiveData
import androidx.room.*
import com.cpu.quikdata.base.BaseDao

@Dao
interface EvacuationCopingDao : BaseDao<EvacuationCoping> {

    @Query("SELECT * FROM evacuation_coping WHERE evacuationId = :evacuationId")
    fun getByEvacuationId(evacuationId: String): LiveData<EvacuationCoping>
}