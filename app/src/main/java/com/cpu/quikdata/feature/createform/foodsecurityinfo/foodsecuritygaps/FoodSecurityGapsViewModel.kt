package com.cpu.quikdata.feature.createform.foodsecurityinfo.foodsecuritygaps

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.cpu.quikdata.data.foodsecurityinfo.foodsecuritygaps.FoodSecurityGaps

class FoodSecurityGapsViewModel(application: Application, formId: String) :
    AndroidViewModel(application) {

    private val mRepository = FoodSecurityGapsRepository(application, formId)

    val foodSecurityGaps : LiveData<FoodSecurityGaps>
        get() = mRepository.foodSecurityGaps

    fun updateFoodSecurityGaps(foodSecurityGaps: FoodSecurityGaps) =
        mRepository.updateData(foodSecurityGaps)
}
