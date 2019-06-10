package com.cpu.quikdata.data.generalinfo.families

import androidx.lifecycle.LiveData
import androidx.room.*
import com.cpu.quikdata.base.BaseDao

@Dao
interface FamiliesDao : BaseDao<Families> {

    @Query("SELECT * FROM families WHERE formId = :formId")
    fun getByFormId(formId: String): LiveData<Families>

    @Query("SELECT * FROM families WHERE formId = :formId")
    fun getByFormIdNonLive(formId: String): Families
}