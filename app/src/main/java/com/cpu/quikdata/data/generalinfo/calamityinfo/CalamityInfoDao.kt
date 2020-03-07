package com.cpu.quikdata.data.generalinfo.calamityinfo

import androidx.lifecycle.LiveData
import androidx.room.*
import com.cpu.quikdata.base.BaseDao

@Dao
interface CalamityInfoDao : BaseDao<CalamityInfo> {

    @Query("SELECT * FROM calamity_info WHERE formId = :formId")
    fun getByFormId(formId: String): LiveData<CalamityInfo>

    @Query("SELECT * FROM calamity_info WHERE formId = :formId")
    suspend fun getByFormIdNonLive(formId: String): CalamityInfo
}