package com.cpu.quikdata.feature.createform.foodsecurityinfo.foodsecurityassistance

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.cpu.quikdata.data.foodsecurityinfo.foodsecurityassistance.FoodSecurityAssistanceRow

class FoodSecurityAssistanceViewModel(application: Application, formId: String) :
    AndroidViewModel(application) {

    private val mRepository = FoodSecurityAssistanceRepository(application, formId)

    val foodSecurityAssistance: LiveData<List<FoodSecurityAssistanceRow>>
        get() = mRepository.foodSecurityAssistance

    fun updateRow(foodSecurityAssistanceRow: FoodSecurityAssistanceRow) = mRepository.updateData(foodSecurityAssistanceRow)

    fun createRow() = mRepository.createData()
}
