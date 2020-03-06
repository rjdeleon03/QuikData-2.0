package com.cpu.quikdata.feature.createform.foodsecurityinfo.foodsecurityimpact

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.cpu.quikdata.data.foodsecurityinfo.foodsecurityimpact.FoodSecurityImpact
import javax.inject.Inject

class FoodSecurityImpactViewModel @Inject constructor(private val mRepository: FoodSecurityImpactRepository)
    : ViewModel() {

    val foodSecurityImpact : LiveData<FoodSecurityImpact>
        get() = mRepository.foodSecurityImpact

    fun updateFoodSecurityImpact(impact: FoodSecurityImpact) = mRepository.updateData(impact)
}
