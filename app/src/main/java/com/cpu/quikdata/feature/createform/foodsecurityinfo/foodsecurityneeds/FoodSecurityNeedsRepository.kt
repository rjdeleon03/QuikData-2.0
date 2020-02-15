package com.cpu.quikdata.feature.createform.foodsecurityinfo.foodsecurityneeds

import androidx.lifecycle.LiveData
import com.cpu.quikdata.base.BaseUpdateableRepository
import com.cpu.quikdata.data.AppDatabase
import com.cpu.quikdata.data.foodsecurityinfo.foodsecurityneeds.FoodSecurityNeeds

class FoodSecurityNeedsRepository(private val mDatabase: AppDatabase, val formId: String) :
    BaseUpdateableRepository<FoodSecurityNeeds>() {

    private val mFoodSecurityNeeds = mDatabase.foodSecurityNeedsDao().getByFormId(formId)

    val foodSecurityNeeds : LiveData<FoodSecurityNeeds>
        get() = mFoodSecurityNeeds

    override suspend fun updateData(data: FoodSecurityNeeds) {
        mFoodSecurityNeeds.value?.let {
            it.copyFrom(data)
            mDatabase.foodSecurityNeedsDao().update(it)
        }
    }
}