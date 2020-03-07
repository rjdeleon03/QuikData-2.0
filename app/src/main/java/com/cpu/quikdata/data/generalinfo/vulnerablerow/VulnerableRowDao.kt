package com.cpu.quikdata.data.generalinfo.vulnerablerow

import androidx.lifecycle.LiveData
import androidx.room.*
import com.cpu.quikdata.base.BaseRowDao

@Dao
interface VulnerableRowDao : BaseRowDao<VulnerableRow> {

    @Query("SELECT * FROM vulnerable_row WHERE formId = :formId ORDER BY type")
    fun getByFormId(formId: String): LiveData<List<VulnerableRow>>

    @Query("SELECT * FROM vulnerable_row WHERE formId = :formId ORDER BY type")
    suspend fun getByFormIdNonLive(formId: String): List<VulnerableRow>
}