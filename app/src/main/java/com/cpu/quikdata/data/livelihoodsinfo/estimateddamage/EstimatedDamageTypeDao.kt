package com.cpu.quikdata.data.livelihoodsinfo.estimateddamage

import androidx.lifecycle.LiveData
import androidx.room.*
import com.cpu.quikdata.base.BaseDao

@Dao
interface EstimatedDamageTypeDao : BaseDao<EstimatedDamageRow> {

    @Query("SELECT * FROM estimated_damage_type WHERE estimatedDamageId = :estimatedDamageId ORDER BY type")
    fun getByEstimatedDamageId(estimatedDamageId: String): LiveData<List<EstimatedDamageRow>>
}