package com.cpu.quikdata.data.health.psychosocialrow

import androidx.lifecycle.LiveData
import androidx.room.*
import com.cpu.quikdata.base.BaseRowDao

@Dao
interface PsychosocialRowDao : BaseRowDao<PsychosocialRow> {

    @Query("SELECT * FROM psychosocial_row WHERE formId = :formId ORDER BY type")
    fun getByFormId(formId: String): LiveData<List<PsychosocialRow>>
}