package com.cpu.quikdata.data.shelterinfo.housedamagerow

import androidx.lifecycle.LiveData
import androidx.room.*
import com.cpu.quikdata.base.BaseDao

@Dao
interface HouseDamageRowDao : BaseDao<HouseDamageRow> {

    @Query("SELECT * FROM house_damage_row WHERE formId = :formId ORDER BY type")
    fun getByFormId(formId: String): LiveData<List<HouseDamageRow>>
}