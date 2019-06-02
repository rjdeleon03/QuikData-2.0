package com.cpu.quikdata.data.foodsecurityinfo.foodsecurityassistance

import androidx.lifecycle.LiveData
import androidx.room.*
import com.cpu.quikdata.base.BaseDao

@Dao
interface FoodSecurityAssistanceRowDao : BaseDao<FoodSecurityAssistanceRow> {

    @Query("SELECT * FROM food_security_assistance_row WHERE formId = :formId ORDER BY dateCreated")
    fun getByFormId(formId: String): LiveData<List<FoodSecurityAssistanceRow>>
}