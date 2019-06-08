package com.cpu.quikdata.data.shelterinfo.shelterassistance

import androidx.lifecycle.LiveData
import androidx.room.*
import com.cpu.quikdata.base.BaseRowDao

@Dao
interface ShelterAssistanceRowDao : BaseRowDao<ShelterAssistanceRow> {

    @Query("SELECT * FROM shelter_assistance_row WHERE formId = :formId ORDER BY dateCreated")
    fun getByFormId(formId: String): LiveData<List<ShelterAssistanceRow>>

    @Query("SELECT * FROM shelter_assistance_row WHERE formId = :formId ORDER BY dateCreated")
    fun getByFormIdNonLive(formId: String): List<ShelterAssistanceRow>
}