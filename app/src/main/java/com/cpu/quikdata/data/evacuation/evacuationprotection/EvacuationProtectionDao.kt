package com.cpu.quikdata.data.evacuation.evacuationprotection

import androidx.lifecycle.LiveData
import androidx.room.*
import com.cpu.quikdata.base.BaseDao

@Dao
interface EvacuationProtectionDao : BaseDao<EvacuationProtection> {

    @Query("SELECT * FROM evacuation_protection WHERE evacuationId = :evacuationId")
    fun getByEvacuationId(evacuationId: String): LiveData<EvacuationProtection>
}