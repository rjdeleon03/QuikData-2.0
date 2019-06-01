package com.cpu.quikdata.data.foodsecurityinfo.foodsecuritygaps

import androidx.lifecycle.LiveData
import androidx.room.*
import com.cpu.quikdata.base.BaseDao

@Dao
interface FoodSecurityGapsDao : BaseDao<FoodSecurityGaps> {

    @Query("SELECT * FROM food_security_gaps WHERE formId = :formId")
    fun getByFormId(formId: String): LiveData<FoodSecurityGaps>
}