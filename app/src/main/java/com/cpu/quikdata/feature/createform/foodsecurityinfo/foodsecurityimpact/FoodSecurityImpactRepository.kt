package com.cpu.quikdata.feature.createform.foodsecurityinfo.foodsecurityimpact

import androidx.lifecycle.LiveData
import com.cpu.quikdata.data.AppDatabase
import com.cpu.quikdata.data.foodsecurityinfo.foodsecurityimpact.FoodSecurityImpact
import com.cpu.quikdata.utils.runOnIoThread
import javax.inject.Inject

class FoodSecurityImpactRepository @Inject constructor(
    private val mDatabase: AppDatabase, formId: String) {

    private val mFoodSecurityImpact = mDatabase.foodSecurityImpactDao().getByFormId(formId)

    val foodSecurityImpact : LiveData<FoodSecurityImpact>
        get() = mFoodSecurityImpact

    fun updateData(data: FoodSecurityImpact) {
        runOnIoThread {
            val oldImpact = mFoodSecurityImpact.value!!
            oldImpact.copyFrom(data)
            mDatabase.foodSecurityImpactDao().update(oldImpact)
        }
    }
}