package com.cpu.quikdata.data.generalinfo.calamityinfo

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface CalamityInfoDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(calamityInfo: CalamityInfo)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun update(calamityInfo: CalamityInfo)

    @Delete
    fun delete(calamityInfo: CalamityInfo)

    @Query("SELECT * FROM calamity_info WHERE formId = :formId")
    fun getByFormId(formId: String): LiveData<CalamityInfo>
}