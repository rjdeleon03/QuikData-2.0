package com.cpu.quikdata.data.form

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface FormDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(form: Form)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun update(form: Form)

    @Delete
    fun delete(form: Form)

    @Query("SELECT * FROM form")
    fun getAll(): LiveData<List<Form>>

    @Query("SELECT * FROM form WHERE id = :id")
    fun getById(id: String): LiveData<List<Form>>
}