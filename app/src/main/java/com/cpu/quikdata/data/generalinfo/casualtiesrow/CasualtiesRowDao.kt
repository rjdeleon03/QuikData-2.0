package com.cpu.quikdata.data.generalinfo.casualtiesrow

import androidx.lifecycle.LiveData
import androidx.room.*
import com.cpu.quikdata.base.BaseRowDao

@Dao
interface CasualtiesRowDao : BaseRowDao<CasualtiesRow> {

    @Query("SELECT * FROM casualties_row WHERE formId = :formId ORDER BY type")
    fun getByFormId(formId: String): LiveData<List<CasualtiesRow>>

    @Query("SELECT * FROM casualties_row WHERE formId = :formId ORDER BY type")
    fun getByFormIdNonLive(formId: String): List<CasualtiesRow>
}