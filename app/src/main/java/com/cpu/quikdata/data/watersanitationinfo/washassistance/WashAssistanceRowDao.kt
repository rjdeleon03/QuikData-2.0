package com.cpu.quikdata.data.watersanitationinfo.washassistance

import androidx.lifecycle.LiveData
import androidx.room.*
import com.cpu.quikdata.base.BaseRowDao

@Dao
interface WashAssistanceRowDao : BaseRowDao<WashAssistanceRow> {

    @Query("SELECT * FROM wash_assistance_row WHERE formId = :formId ORDER BY dateCreated")
    fun getByFormId(formId: String): LiveData<List<WashAssistanceRow>>

    @Query("SELECT * FROM wash_assistance_row WHERE formId = :formId ORDER BY dateCreated")
    fun getByFormIdNonLive(formId: String): List<WashAssistanceRow>
}