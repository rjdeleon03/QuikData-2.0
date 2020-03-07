package com.cpu.quikdata.data.foodsecurityinfo.foodsecurityneeds

import androidx.lifecycle.LiveData
import androidx.room.*
import com.cpu.quikdata.base.BaseDao

@Dao
interface FoodSecurityNeedsDao : BaseDao<FoodSecurityNeeds> {

    @Query("SELECT * FROM food_security_needs WHERE formId = :formId")
    fun getByFormId(formId: String): LiveData<FoodSecurityNeeds>

    @Query("SELECT * FROM food_security_needs WHERE formId = :formId")
    suspend fun getByFormIdNonLive(formId: String): FoodSecurityNeeds
}