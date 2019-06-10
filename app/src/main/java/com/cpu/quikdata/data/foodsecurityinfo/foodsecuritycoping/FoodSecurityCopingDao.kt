package com.cpu.quikdata.data.foodsecurityinfo.foodsecuritycoping

import androidx.lifecycle.LiveData
import androidx.room.*
import com.cpu.quikdata.base.BaseDao

@Dao
interface FoodSecurityCopingDao : BaseDao<FoodSecurityCoping> {

    @Query("SELECT * FROM food_security_coping WHERE formId = :formId")
    fun getByFormId(formId: String): LiveData<FoodSecurityCoping>

    @Query("SELECT * FROM food_security_coping WHERE formId = :formId")
    fun getByFormIdNonLive(formId: String): FoodSecurityCoping
}