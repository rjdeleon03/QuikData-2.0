package com.cpu.quikdata.feature.createform.foodsecurityinfo.foodsecurityneeds

import androidx.lifecycle.LiveData
import com.cpu.quikdata.data.AppDatabase
import com.cpu.quikdata.data.foodsecurityinfo.foodsecurityneeds.FoodSecurityNeeds
import javax.inject.Inject

class FoodSecurityNeedsRepository @Inject constructor(
    private val mDatabase: AppDatabase,
    formId: String
) {

    private val mFoodSecurityNeeds = mDatabase.foodSecurityNeedsDao().getByFormId(formId)

    val foodSecurityNeeds: LiveData<FoodSecurityNeeds>
        get() = mFoodSecurityNeeds

    suspend fun updateData(data: FoodSecurityNeeds) {
        mFoodSecurityNeeds.value?.apply {
            copyFrom(data)
            mDatabase.foodSecurityNeedsDao().update(this)
        }
    }
}