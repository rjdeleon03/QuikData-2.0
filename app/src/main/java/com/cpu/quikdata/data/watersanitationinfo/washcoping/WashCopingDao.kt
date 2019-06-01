package com.cpu.quikdata.data.watersanitationinfo.washcoping

import androidx.lifecycle.LiveData
import androidx.room.*
import com.cpu.quikdata.base.BaseDao

@Dao
interface WashCopingDao : BaseDao<WashCoping> {

    @Query("SELECT * FROM wash_coping WHERE formId = :formId")
    fun getByFormId(formId: String): LiveData<WashCoping>
}