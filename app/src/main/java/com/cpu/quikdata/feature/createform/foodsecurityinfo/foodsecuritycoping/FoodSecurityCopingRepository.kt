package com.cpu.quikdata.feature.createform.foodsecurityinfo.foodsecuritycoping

import androidx.lifecycle.LiveData
import com.cpu.quikdata.base.BaseUpdateableRepository
import com.cpu.quikdata.data.AppDatabase
import com.cpu.quikdata.data.foodsecurityinfo.foodsecuritycoping.FoodSecurityCoping
import com.cpu.quikdata.utils.runOnIoThread

class FoodSecurityCopingRepository(private val mDatabase: AppDatabase, val formId: String) :
    BaseUpdateableRepository<FoodSecurityCoping>() {

    private val mFoodSecurityCoping = mDatabase.foodSecurityCopingDao().getByFormId(formId)

    val foodSecurityCoping : LiveData<FoodSecurityCoping>
        get() = mFoodSecurityCoping

    override fun updateData(data: FoodSecurityCoping) {
        runOnIoThread {
            val oldCoping = mFoodSecurityCoping.value!!
            oldCoping.copyFrom(data)
            mDatabase.foodSecurityCopingDao().update(oldCoping)
        }
    }
}