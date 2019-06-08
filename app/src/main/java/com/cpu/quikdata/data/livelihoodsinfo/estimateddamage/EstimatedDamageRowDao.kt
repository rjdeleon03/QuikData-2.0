package com.cpu.quikdata.data.livelihoodsinfo.estimateddamage

import androidx.lifecycle.LiveData
import androidx.room.*
import com.cpu.quikdata.base.BaseRowDao

@Dao
interface EstimatedDamageRowDao : BaseRowDao<EstimatedDamageRow> {

    @Transaction
    @Query("SELECT * FROM estimated_damage_row WHERE formId = :formId ORDER BY type")
    fun getByFormId(formId: String): LiveData<List<EstimatedDamageComplete>>

    @Query("SELECT * FROM estimated_damage_row WHERE formId = :formId ORDER BY type")
    fun getByFormIdNonLive(formId: String): List<EstimatedDamageComplete>
}