package com.cpu.quikdata.data.generalinfo.population.row

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

    @Query("SELECT * FROM population_row WHERE populationId = :formId")
    fun getByPopulationId(formId: String): LiveData<PopulationRow>
}