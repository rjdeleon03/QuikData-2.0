package com.cpu.quikdata.feature.createform.livelihoodsinfo.incomeafter

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.cpu.quikdata.data.livelihoodsinfo.incomeafter.IncomeAfterRow
import javax.inject.Inject

class IncomeAfterViewModel @Inject constructor (private val mRepository: IncomeAfterRepository)
    : ViewModel() {

    val incomeAfter: LiveData<List<IncomeAfterRow>>
        get() = mRepository.incomeAfter

    fun updateRow(incomeAfterRow: IncomeAfterRow) = mRepository.updateData(incomeAfterRow)

    fun createRow() = mRepository.createData()

    fun deleteRow(row: IncomeAfterRow) = mRepository.deleteData(row)
}