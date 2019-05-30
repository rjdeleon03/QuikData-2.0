package com.cpu.quikdata.data.generalinfo.infrastructuredamage

import androidx.lifecycle.LiveData
import androidx.room.*
import com.cpu.quikdata.base.BaseDao

@Dao
interface InfrastructureDamageRowDao : BaseDao<InfrastructureDamageRow> {

    @Query("SELECT * FROM infrastructure_damage_row WHERE formId = :formId ORDER BY type")
    fun getByFormId(formId: String): LiveData<List<InfrastructureDamageRow>>
}