package com.cpu.quikdata.data.livelihoodsinfo.estimateddamage

import androidx.lifecycle.LiveData
import androidx.room.*
import com.cpu.quikdata.base.BaseDao

@Dao
interface EstimatedDamageRowDao : BaseDao<EstimatedDamageRow> {

    @Transaction
    @Query("SELECT * FROM estimated_damage_row WHERE formId = :formId ORDER BY type")
    fun getByFormId(formId: String): LiveData<List<EstimatedDamageComplete>>
}