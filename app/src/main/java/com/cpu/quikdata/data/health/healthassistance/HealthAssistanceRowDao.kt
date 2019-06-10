package com.cpu.quikdata.data.health.healthassistance

import androidx.lifecycle.LiveData
import androidx.room.*
import com.cpu.quikdata.base.BaseRowDao

@Dao
interface HealthAssistanceRowDao : BaseRowDao<HealthAssistanceRow> {

    @Query("SELECT * FROM health_assistance_row WHERE formId = :formId ORDER BY dateCreated")
    fun getByFormId(formId: String): LiveData<List<HealthAssistanceRow>>

    @Query("SELECT * FROM health_assistance_row WHERE formId = :formId ORDER BY dateCreated")
    fun getByFormIdNonLive(formId: String): List<HealthAssistanceRow>
}