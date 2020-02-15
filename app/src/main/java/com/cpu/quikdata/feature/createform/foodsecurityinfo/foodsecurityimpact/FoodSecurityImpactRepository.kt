package com.cpu.quikdata.feature.createform.foodsecurityinfo.foodsecurityimpact

import androidx.lifecycle.LiveData
import com.cpu.quikdata.base.BaseUpdateableRepository
import com.cpu.quikdata.data.AppDatabase
import com.cpu.quikdata.data.foodsecurityinfo.foodsecurityimpact.FoodSecurityImpact

class FoodSecurityImpactRepository(private val mDatabase: AppDatabase, val formId: String) :
    BaseUpdateableRepository<FoodSecurityImpact>() {

    private val mFoodSecurityImpact = mDatabase.foodSecurityImpactDao().getByFormId(formId)

    val foodSecurityImpact : LiveData<FoodSecurityImpact>
        get() = mFoodSecurityImpact

    override suspend fun updateData(data: FoodSecurityImpact) {
        mFoodSecurityImpact.value?.let {
            it.copyFrom(data)
            mDatabase.foodSecurityImpactDao().update(it)
        }
    }
}