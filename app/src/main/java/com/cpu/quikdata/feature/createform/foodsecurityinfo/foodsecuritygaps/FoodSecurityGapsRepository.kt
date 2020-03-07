package com.cpu.quikdata.feature.createform.foodsecurityinfo.foodsecuritygaps

import androidx.lifecycle.LiveData
import com.cpu.quikdata.data.AppDatabase
import com.cpu.quikdata.data.foodsecurityinfo.foodsecuritygaps.FoodSecurityGaps
import com.cpu.quikdata.utils.runOnIoThread
import javax.inject.Inject

class FoodSecurityGapsRepository @Inject constructor(private val mDatabase: AppDatabase, formId: String) {

    private val mFoodSecurityGaps = mDatabase.foodSecurityGapsDao().getByFormId(formId)

    val foodSecurityGaps : LiveData<FoodSecurityGaps>
        get() = mFoodSecurityGaps

    fun updateData(data: FoodSecurityGaps) {
        runOnIoThread {
            val oldGaps = mFoodSecurityGaps.value!!
            oldGaps.copyFrom(data)
            mDatabase.foodSecurityGapsDao().update(oldGaps)
        }
    }
}