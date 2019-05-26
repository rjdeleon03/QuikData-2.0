package com.cpu.quikdata.data.generalinfo

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface GeneralInfoDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(generalInfo: GeneralInfo)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun update(generalInfo: GeneralInfo)

    @Delete
    fun delete(generalInfo: GeneralInfo)

    @Query("SELECT * FROM general_info WHERE formId = :formId")
    fun getByFormId(formId: String): LiveData<GeneralInfo>
}