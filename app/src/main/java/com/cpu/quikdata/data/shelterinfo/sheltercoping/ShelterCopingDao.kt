package com.cpu.quikdata.data.shelterinfo.sheltercoping

import androidx.lifecycle.LiveData
import androidx.room.*
import com.cpu.quikdata.base.BaseDao

@Dao
interface ShelterCopingDao : BaseDao<ShelterCoping> {

    @Query("SELECT * FROM shelter_coping WHERE formId = :formId")
    fun getByFormId(formId: String): LiveData<ShelterCoping>

    @Query("SELECT * FROM shelter_coping WHERE formId = :formId")
    suspend fun getByFormIdNonLive(formId: String): ShelterCoping
}