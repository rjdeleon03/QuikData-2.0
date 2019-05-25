package com.cpu.quikdata.data.formdetails

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface FormDetailsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(formDetails: FormDetails)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun update(formDetails: FormDetails)

    @Delete
    fun delete(formDetails: FormDetails)

    @Query("SELECT * FROM form_details WHERE id = :id")
    fun getById(id: String): LiveData<List<FormDetails>>
}