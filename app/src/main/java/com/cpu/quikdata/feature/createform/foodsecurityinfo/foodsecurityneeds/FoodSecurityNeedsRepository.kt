package com.cpu.quikdata.feature.createform.foodsecurityinfo.foodsecurityneeds

import androidx.lifecycle.LiveData
import com.cpu.quikdata.base.BaseUpdateableRepository
import com.cpu.quikdata.data.AppDatabase
import com.cpu.quikdata.data.foodsecurityinfo.foodsecurityneeds.FoodSecurityNeeds
import com.cpu.quikdata.utils.runOnIoThread

class FoodSecurityNeedsRepository(private val mDatabase: AppDatabase, val formId: String) :
    BaseUpdateableRepository<FoodSecurityNeeds>() {

    private val mFoodSecurityNeeds = mDatabase.foodSecurityNeedsDao().getByFormId(formId)

    val foodSecurityNeeds : LiveData<FoodSecurityNeeds>
        get() = mFoodSecurityNeeds

    override fun updateData(data: FoodSecurityNeeds) {
        runOnIoThread {
            val oldNeeds = mFoodSecurityNeeds.value!!
            oldNeeds.copyFrom(data)
            mDatabase.foodSecurityNeedsDao().update(oldNeeds)
        }
    }
}