package com.cpu.quikdata.feature.createform.foodsecurityinfo.foodsecurityneeds

import android.app.Application
import androidx.lifecycle.LiveData
import com.cpu.quikdata.base.BaseRepository
import com.cpu.quikdata.data.foodsecurityinfo.foodsecurityneeds.FoodSecurityNeeds
import com.cpu.quikdata.utils.runOnIoThread

class FoodSecurityNeedsRepository(application: Application, formId: String) :
    BaseRepository<FoodSecurityNeeds>(application) {

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