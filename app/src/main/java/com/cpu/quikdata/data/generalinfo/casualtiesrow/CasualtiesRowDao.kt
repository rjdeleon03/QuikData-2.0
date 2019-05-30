package com.cpu.quikdata.data.generalinfo.casualtiesrow

import androidx.lifecycle.LiveData
import androidx.room.*
import com.cpu.quikdata.base.BaseDao

@Dao
interface CasualtiesRowDao : BaseDao<CasualtiesRow> {

    @Query("SELECT * FROM casualties_row WHERE formId = :formId ORDER BY type")
    fun getByFormId(formId: String): LiveData<List<CasualtiesRow>>
}