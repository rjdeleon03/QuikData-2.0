package com.cpu.quikdata.feature.createform.foodsecurityinfo.foodsecuritycoping

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.cpu.quikdata.data.foodsecurityinfo.foodsecuritycoping.FoodSecurityCoping
import javax.inject.Inject

class FoodSecurityCopingViewModel @Inject constructor(private val mRepository: FoodSecurityCopingRepository)
    : ViewModel() {

    val foodSecurityCoping : LiveData<FoodSecurityCoping>
        get() = mRepository.foodSecurityCoping

    fun updateFoodSecurityCoping(foodSecurityCoping: FoodSecurityCoping) =
        mRepository.updateData(foodSecurityCoping)
}
