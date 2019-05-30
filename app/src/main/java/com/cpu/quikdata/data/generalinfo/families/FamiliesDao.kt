package com.cpu.quikdata.data.generalinfo.families

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface FamiliesDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(families: Families)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun update(families: Families)

    @Delete
    fun delete(families: Families)

    @Query("SELECT * FROM families WHERE formId = :formId")
    fun getByFormId(formId: String): LiveData<Families>
}