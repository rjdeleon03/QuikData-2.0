package com.cpu.quikdata.data.health.diseasesrow

import androidx.lifecycle.LiveData
import androidx.room.*
import com.cpu.quikdata.base.BaseDao

@Dao
interface DiseasesRowDao : BaseDao<DiseasesRow> {

    @Query("SELECT * FROM diseases_row WHERE formId = :formId ORDER BY type")
    fun getByFormId(formId: String): LiveData<List<DiseasesRow>>
}