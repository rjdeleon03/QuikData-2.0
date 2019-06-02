package com.cpu.quikdata.data.watersanitationinfo.washassistance

import androidx.lifecycle.LiveData
import androidx.room.*
import com.cpu.quikdata.base.BaseDao

@Dao
interface WashAssistanceRowDao : BaseDao<WashAssistanceRow> {

    @Query("SELECT * FROM wash_assistance_row WHERE formId = :formId ORDER BY dateCreated")
    fun getByFormId(formId: String): LiveData<List<WashAssistanceRow>>
}