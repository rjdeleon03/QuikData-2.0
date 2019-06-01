package com.cpu.quikdata.data.watersanitationinfo.washconditions

import androidx.lifecycle.LiveData
import androidx.room.*
import com.cpu.quikdata.base.BaseDao

@Dao
interface WashConditionsDao : BaseDao<WashConditions> {

    @Query("SELECT * FROM wash_conditions WHERE formId = :formId")
    fun getByFormId(formId: String): LiveData<WashConditions>
}