package com.cpu.quikdata.feature.createform.foodsecurityinfo.foodsecuritycoping

import androidx.lifecycle.LiveData
import com.cpu.quikdata.data.AppDatabase
import com.cpu.quikdata.data.foodsecurityinfo.foodsecuritycoping.FoodSecurityCoping
import com.cpu.quikdata.utils.runOnIoThread
import javax.inject.Inject

class FoodSecurityCopingRepository @Inject constructor(
    private val mDatabase: AppDatabase, formId: String) {

    private val mFoodSecurityCoping = mDatabase.foodSecurityCopingDao().getByFormId(formId)

    val foodSecurityCoping : LiveData<FoodSecurityCoping>
        get() = mFoodSecurityCoping

    fun updateData(data: FoodSecurityCoping) {
        runOnIoThread {
            val oldCoping = mFoodSecurityCoping.value!!
            oldCoping.copyFrom(data)
            mDatabase.foodSecurityCopingDao().update(oldCoping)
        }
    }
}