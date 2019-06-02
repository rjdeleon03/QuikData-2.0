package com.cpu.quikdata.feature.createform.foodsecurityinfo.foodsecuritygaps

import android.app.Application
import androidx.lifecycle.LiveData
import com.cpu.quikdata.base.BaseRepository
import com.cpu.quikdata.data.foodsecurityinfo.foodsecuritygaps.FoodSecurityGaps
import com.cpu.quikdata.utils.runOnIoThread

class FoodSecurityGapsRepository(application: Application, formId: String) :
    BaseRepository<FoodSecurityGaps>(application) {

    private val mFoodSecurityGaps = mDatabase.foodSecurityGapsDao().getByFormId(formId)

    val foodSecurityGaps : LiveData<FoodSecurityGaps>
        get() = mFoodSecurityGaps

    override fun updateData(data: FoodSecurityGaps) {
        runOnIoThread {
            val oldGaps = mFoodSecurityGaps.value!!
            oldGaps.copyFrom(data)
            mDatabase.foodSecurityGapsDao().update(oldGaps)
        }
    }
}