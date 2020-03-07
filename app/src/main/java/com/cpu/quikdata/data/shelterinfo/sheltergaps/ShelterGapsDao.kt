package com.cpu.quikdata.data.shelterinfo.sheltergaps

import androidx.lifecycle.LiveData
import androidx.room.*
import com.cpu.quikdata.base.BaseDao

@Dao
interface ShelterGapsDao : BaseDao<ShelterGaps> {

    @Query("SELECT * FROM shelter_gaps WHERE formId = :formId")
    fun getByFormId(formId: String): LiveData<ShelterGaps>

    @Query("SELECT * FROM shelter_gaps WHERE formId = :formId")
    suspend fun getByFormIdNonLive(formId: String): ShelterGaps
}