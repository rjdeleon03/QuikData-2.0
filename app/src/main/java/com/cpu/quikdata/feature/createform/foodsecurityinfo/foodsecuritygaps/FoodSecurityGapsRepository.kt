package com.cpu.quikdata.feature.createform.foodsecurityinfo.foodsecuritygaps

import androidx.lifecycle.LiveData
import com.cpu.quikdata.base.BaseUpdateableRepository
import com.cpu.quikdata.data.AppDatabase
import com.cpu.quikdata.data.foodsecurityinfo.foodsecuritygaps.FoodSecurityGaps

class FoodSecurityGapsRepository(private val mDatabase: AppDatabase, val formId: String) :
    BaseUpdateableRepository<FoodSecurityGaps>() {

    private val mFoodSecurityGaps = mDatabase.foodSecurityGapsDao().getByFormId(formId)

    val foodSecurityGaps : LiveData<FoodSecurityGaps>
        get() = mFoodSecurityGaps

    override suspend fun updateData(data: FoodSecurityGaps) {
        mFoodSecurityGaps.value?.let {
            it.copyFrom(data)
            mDatabase.foodSecurityGapsDao().update(it)
        }
    }
}