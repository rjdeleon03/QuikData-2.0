package com.cpu.quikdata.feature.createform.foodsecurityinfo.foodsecuritycoping

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.cpu.quikdata.data.foodsecurityinfo.foodsecuritycoping.FoodSecurityCoping

class FoodSecurityCopingViewModel(application: Application, formId: String) :
    AndroidViewModel(application) {

    private val mRepository = FoodSecurityCopingRepository(application, formId)

    val foodSecurityCoping : LiveData<FoodSecurityCoping>
        get() = mRepository.foodSecurityCoping

    fun updateFoodSecurityCoping(foodSecurityCoping: FoodSecurityCoping) =
        mRepository.updateData(foodSecurityCoping)
}
