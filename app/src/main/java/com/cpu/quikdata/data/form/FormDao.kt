package com.cpu.quikdata.data.form

import androidx.lifecycle.LiveData
import androidx.room.*
import com.cpu.quikdata.data.generalinfo.GeneralInfoComplete

@Dao
interface FormDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(form: Form)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun update(form: Form)

    @Delete
    suspend fun delete(form: Form)

    @Transaction
    @Query("SELECT * FROM form WHERE isTemporary = 0 ORDER BY dateCreated DESC")
    fun getAllActual(): LiveData<List<FormComplete>>

    @Query("SELECT * FROM form WHERE id = :id")
    fun getById(id: String): LiveData<Form>

    @Query("SELECT * FROM form WHERE id = :id")
    fun getByIdSingle(id: String): Form

    @Transaction
    @Query("SELECT * FROM form WHERE id = :formId")
    fun getFormDataNonLive(formId: String): FormComplete

    @Transaction
    @Query("SELECT * FROM form WHERE id = :formId")
    fun getGeneralInfoNonLive(formId: String): GeneralInfoComplete
}