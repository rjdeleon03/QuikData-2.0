package com.cpu.quikdata.data.foodsecurityinfo.foodsecurityimpact

import androidx.lifecycle.LiveData
import androidx.room.*
import com.cpu.quikdata.base.BaseDao

@Dao
interface FoodSecurityImpactDao : BaseDao<FoodSecurityImpact> {

    @Query("SELECT * FROM food_security_impact WHERE formId = :formId")
    fun getByFormId(formId: String): LiveData<FoodSecurityImpact>
}