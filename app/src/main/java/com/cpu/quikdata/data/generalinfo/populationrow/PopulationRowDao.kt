package com.cpu.quikdata.data.generalinfo.populationrow

import androidx.lifecycle.LiveData
import androidx.room.*
import com.cpu.quikdata.base.BaseDao

@Dao
interface PopulationRowDao : BaseDao<PopulationRow> {

    @Query("SELECT * FROM population_row WHERE formId = :formId ORDER BY type")
    fun getByFormId(formId: String): LiveData<List<PopulationRow>>
}