package com.cpu.quikdata.feature.createform.foodsecurityinfo.foodsecurityneeds

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.cpu.quikdata.common.runOnIoThread
import com.cpu.quikdata.data.foodsecurityinfo.foodsecurityneeds.FoodSecurityNeeds
import javax.inject.Inject

class FoodSecurityNeedsViewModel @Inject constructor (private val mRepository: FoodSecurityNeedsRepository)
    : ViewModel() {

    val foodSecurityNeeds : LiveData<FoodSecurityNeeds>
        get() = mRepository.foodSecurityNeeds

    fun updateFoodSecurityNeeds(foodSecurityNeeds: FoodSecurityNeeds) =
        runOnIoThread { mRepository.updateData(foodSecurityNeeds) }
}
