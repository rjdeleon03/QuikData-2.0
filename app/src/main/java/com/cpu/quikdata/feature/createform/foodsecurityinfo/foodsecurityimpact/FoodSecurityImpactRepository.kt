package com.cpu.quikdata.feature.createform.foodsecurityinfo.foodsecurityimpact

import androidx.lifecycle.LiveData
import com.cpu.quikdata.base.BaseUpdateableRepository
import com.cpu.quikdata.data.AppDatabase
import com.cpu.quikdata.data.foodsecurityinfo.foodsecurityimpact.FoodSecurityImpact
import com.cpu.quikdata.utils.runOnIoThread

class FoodSecurityImpactRepository(private val mDatabase: AppDatabase, val formId: String) :
    BaseUpdateableRepository<FoodSecurityImpact>() {

    private val mFoodSecurityImpact = mDatabase.foodSecurityImpactDao().getByFormId(formId)

    val foodSecurityImpact : LiveData<FoodSecurityImpact>
        get() = mFoodSecurityImpact

    override fun updateData(data: FoodSecurityImpact) {
        runOnIoThread {
            val oldImpact = mFoodSecurityImpact.value!!
            oldImpact.copyFrom(data)
            mDatabase.foodSecurityImpactDao().update(oldImpact)
        }
    }
}