package com.cpu.quikdata.data.generalinfo.population

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface PopulationDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(population: Population)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun update(population: Population)

    @Delete
    fun delete(population: Population)

    @Query("SELECT * FROM population WHERE formId = :formId")
    fun getByFormId(formId: String): LiveData<PopulationComplete>
}