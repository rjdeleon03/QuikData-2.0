package com.cpu.quikdata.data.generalinfo.causeofdeath

import androidx.lifecycle.LiveData
import androidx.room.*
import com.cpu.quikdata.base.BaseRowDao

@Dao
interface CauseOfDeathRowDao : BaseRowDao<CauseOfDeathRow> {

    @Query("SELECT * FROM cause_of_death_row WHERE formId = :formId ORDER BY type")
    fun getByFormId(formId: String): LiveData<List<CauseOfDeathRow>>

    @Query("SELECT * FROM cause_of_death_row WHERE formId = :formId ORDER BY type")
    suspend fun getByFormIdNonLive(formId: String): List<CauseOfDeathRow>
}