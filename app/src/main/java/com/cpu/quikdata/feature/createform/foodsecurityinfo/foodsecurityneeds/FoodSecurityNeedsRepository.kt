package com.cpu.quikdata.feature.createform.foodsecurityinfo.foodsecurityneeds

import androidx.lifecycle.LiveData
import com.cpu.quikdata.data.AppDatabase
import com.cpu.quikdata.data.foodsecurityinfo.foodsecurityneeds.FoodSecurityNeeds
import com.cpu.quikdata.utils.runOnIoThread
import javax.inject.Inject

class FoodSecurityNeedsRepository @Inject constructor(private val mDatabase: AppDatabase, formId: String) {

    private val mFoodSecurityNeeds = mDatabase.foodSecurityNeedsDao().getByFormId(formId)

    val foodSecurityNeeds : LiveData<FoodSecurityNeeds>
        get() = mFoodSecurityNeeds

    fun updateData(data: FoodSecurityNeeds) {
        runOnIoThread {
            val oldNeeds = mFoodSecurityNeeds.value!!
            oldNeeds.copyFrom(data)
            mDatabase.foodSecurityNeedsDao().update(oldNeeds)
        }
    }
}