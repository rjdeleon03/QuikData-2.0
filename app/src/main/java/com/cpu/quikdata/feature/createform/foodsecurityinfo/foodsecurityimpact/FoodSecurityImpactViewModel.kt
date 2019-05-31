package com.cpu.quikdata.feature.createform.foodsecurityinfo.foodsecurityimpact

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.cpu.quikdata.data.foodsecurityinfo.foodsecurityimpact.FoodSecurityImpact

class FoodSecurityImpactViewModel(application: Application, formId: String) :
    AndroidViewModel(application) {

    private val mRepository = FoodSecurityImpactRepository(application, formId)

    val impact : LiveData<FoodSecurityImpact>
        get() = mRepository.impact

    fun updateFoodSecurityImpact(impact: FoodSecurityImpact) = mRepository.updateData(impact)
}
