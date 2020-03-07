package com.cpu.quikdata.data.generalinfo.infrastructuredamage

import androidx.lifecycle.LiveData
import androidx.room.*
import com.cpu.quikdata.base.BaseRowDao

@Dao
interface InfrastructureDamageRowDao : BaseRowDao<InfrastructureDamageRow> {

    @Query("SELECT * FROM infrastructure_damage_row WHERE formId = :formId ORDER BY type")
    fun getByFormId(formId: String): LiveData<List<InfrastructureDamageRow>>

    @Query("SELECT * FROM infrastructure_damage_row WHERE formId = :formId ORDER BY type")
    suspend fun getByFormIdNonLive(formId: String): List<InfrastructureDamageRow>
}