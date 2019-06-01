package com.cpu.quikdata.feature.createform.foodsecurityinfo.foodsecurityneeds

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.cpu.quikdata.data.foodsecurityinfo.foodsecurityneeds.FoodSecurityNeeds

class FoodSecurityNeedsViewModel(application: Application, formId: String) :
    AndroidViewModel(application) {

    private val mRepository = FoodSecurityNeedsRepository(application, formId)

    val foodSecurityNeeds : LiveData<FoodSecurityNeeds>
        get() = mRepository.foodSecurityNeeds

    fun updateFoodSecurityNeeds(foodSecurityNeeds: FoodSecurityNeeds) =
        mRepository.updateData(foodSecurityNeeds)
}
