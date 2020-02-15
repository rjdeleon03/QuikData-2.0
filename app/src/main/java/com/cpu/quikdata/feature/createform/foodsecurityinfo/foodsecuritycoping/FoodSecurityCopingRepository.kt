package com.cpu.quikdata.feature.createform.foodsecurityinfo.foodsecuritycoping

import androidx.lifecycle.LiveData
import com.cpu.quikdata.base.BaseUpdateableRepository
import com.cpu.quikdata.data.AppDatabase
import com.cpu.quikdata.data.foodsecurityinfo.foodsecuritycoping.FoodSecurityCoping

class FoodSecurityCopingRepository(private val mDatabase: AppDatabase, val formId: String) :
    BaseUpdateableRepository<FoodSecurityCoping>() {

    private val mFoodSecurityCoping = mDatabase.foodSecurityCopingDao().getByFormId(formId)

    val foodSecurityCoping : LiveData<FoodSecurityCoping>
        get() = mFoodSecurityCoping

    override suspend fun updateData(data: FoodSecurityCoping) {
        mFoodSecurityCoping.value?.let{
            it.copyFrom(data)
            mDatabase.foodSecurityCopingDao().update(it)
        }
    }
}