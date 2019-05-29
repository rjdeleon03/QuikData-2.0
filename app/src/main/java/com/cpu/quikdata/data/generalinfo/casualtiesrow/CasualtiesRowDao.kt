package com.cpu.quikdata.data.generalinfo.casualtiesrow

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface CasualtiesRowDao: BaseRowDao<CasualtiesRow>  {

    @Query("SELECT * FROM casualties_row WHERE formId = :formId ORDER BY type")
    fun getByFormId(formId: String): LiveData<List<CasualtiesRow>>
}