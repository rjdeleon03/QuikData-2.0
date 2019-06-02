package com.cpu.quikdata.data.health.healthcoping

import androidx.lifecycle.LiveData
import androidx.room.*
import com.cpu.quikdata.base.BaseDao

@Dao
interface HealthCopingDao : BaseDao<HealthCoping> {

    @Query("SELECT * FROM health_coping WHERE formId = :formId")
    fun getByFormId(formId: String): LiveData<HealthCoping>
}