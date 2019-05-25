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

    @Query("SELECT * FROM form_details WHERE formId = :formId")
    fun getByFormId(formId: String): LiveData<FormDetails>
}