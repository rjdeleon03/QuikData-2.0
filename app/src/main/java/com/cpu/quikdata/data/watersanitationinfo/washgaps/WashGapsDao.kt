package com.cpu.quikdata.data.watersanitationinfo.washgaps

import androidx.lifecycle.LiveData
import androidx.room.*
import com.cpu.quikdata.base.BaseDao

@Dao
interface WashGapsDao : BaseDao<WashGaps> {

    @Query("SELECT * FROM wash_gaps WHERE formId = :formId")
    fun getByFormId(formId: String): LiveData<WashGaps>
}