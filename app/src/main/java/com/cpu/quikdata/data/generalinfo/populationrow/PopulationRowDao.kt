package com.cpu.quikdata.data.generalinfo.populationrow

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface PopulationRowDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(populationRow: PopulationRow)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun update(populationRow: PopulationRow)

    @Delete
    fun delete(populationRow: PopulationRow)

    @Query("SELECT * FROM population_row WHERE formId = :formId ORDER BY type")
    fun getByFormId(formId: String): LiveData<List<PopulationRow>>
}