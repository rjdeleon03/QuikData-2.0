package com.cpu.quikdata.data.baselinedata

import androidx.lifecycle.LiveData
import androidx.room.*
import com.cpu.quikdata.base.BaseDao

@Dao
interface BaselineDataDao : BaseDao<BaselineData> {

    @Query("SELECT * FROM baseline_data WHERE formId = :formId")
    fun getByFormId(formId: String): LiveData<BaselineData>

    @Query("SELECT * FROM baseline_data WHERE formId = :formId")
    suspend fun getByFormIdNonLive(formId: String): BaselineData
}