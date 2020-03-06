package com.cpu.quikdata.feature.createform.foodsecurityinfo.foodsecurityassistance

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.cpu.quikdata.data.foodsecurityinfo.foodsecurityassistance.FoodSecurityAssistanceRow
import javax.inject.Inject

class FoodSecurityAssistanceViewModel @Inject constructor(private val mRepository: FoodSecurityAssistanceRepository)
    : ViewModel() {

    val foodSecurityAssistance: LiveData<List<FoodSecurityAssistanceRow>>
        get() = mRepository.foodSecurityAssistance

    fun updateRow(foodSecurityAssistanceRow: FoodSecurityAssistanceRow) = mRepository.updateData(foodSecurityAssistanceRow)

    fun createRow() = mRepository.createData()

    fun deleteRow(row: FoodSecurityAssistanceRow) = mRepository.deleteData(row)
}
