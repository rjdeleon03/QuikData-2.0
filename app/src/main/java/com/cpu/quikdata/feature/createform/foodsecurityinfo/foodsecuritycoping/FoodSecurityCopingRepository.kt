package com.cpu.quikdata.feature.createform.foodsecurityinfo.foodsecuritycoping

import android.app.Application
import androidx.lifecycle.LiveData
import com.cpu.quikdata.base.BaseRepository
import com.cpu.quikdata.data.foodsecurityinfo.foodsecuritycoping.FoodSecurityCoping
import com.cpu.quikdata.utils.runOnIoThread

class FoodSecurityCopingRepository(application: Application, formId: String) :
    BaseRepository<FoodSecurityCoping>(application) {

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