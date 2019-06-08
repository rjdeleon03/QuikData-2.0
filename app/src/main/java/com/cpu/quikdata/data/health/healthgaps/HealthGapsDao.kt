package com.cpu.quikdata.data.health.healthgaps

import androidx.lifecycle.LiveData
import androidx.room.*
import com.cpu.quikdata.base.BaseDao

@Dao
interface HealthGapsDao : BaseDao<HealthGaps> {

    @Query("SELECT * FROM health_gaps WHERE formId = :formId")
    fun getByFormId(formId: String): LiveData<HealthGaps>

    @Query("SELECT * FROM health_gaps WHERE formId = :formId")
    fun getByFormIdNonLive(formId: String): HealthGaps
}