package com.cpu.quikdata.feature.createform.foodsecurityinfo.foodsecuritygaps

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.cpu.quikdata.common.runOnIoThread
import com.cpu.quikdata.data.foodsecurityinfo.foodsecuritygaps.FoodSecurityGaps
import javax.inject.Inject

class FoodSecurityGapsViewModel @Inject constructor (private val mRepository: FoodSecurityGapsRepository)
    : ViewModel() {

    val foodSecurityGaps : LiveData<FoodSecurityGaps>
        get() = mRepository.foodSecurityGaps

    fun updateFoodSecurityGaps(foodSecurityGaps: FoodSecurityGaps) =
        runOnIoThread { mRepository.updateData(foodSecurityGaps) }
}
