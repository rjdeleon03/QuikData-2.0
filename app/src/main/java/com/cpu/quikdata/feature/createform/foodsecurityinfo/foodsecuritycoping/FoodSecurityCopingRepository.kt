package com.cpu.quikdata.feature.createform.foodsecurityinfo.foodsecuritycoping

import androidx.lifecycle.LiveData
import com.cpu.quikdata.data.AppDatabase
import com.cpu.quikdata.data.foodsecurityinfo.foodsecuritycoping.FoodSecurityCoping
import javax.inject.Inject

class FoodSecurityCopingRepository @Inject constructor(
    private val mDatabase: AppDatabase, formId: String
) {

    private val mFoodSecurityCoping = mDatabase.foodSecurityCopingDao().getByFormId(formId)

    val foodSecurityCoping: LiveData<FoodSecurityCoping>
        get() = mFoodSecurityCoping

    suspend fun updateData(data: FoodSecurityCoping) {
        mFoodSecurityCoping.value?.apply {
            copyFrom(data)
            mDatabase.foodSecurityCopingDao().update(this)
        }
    }
}