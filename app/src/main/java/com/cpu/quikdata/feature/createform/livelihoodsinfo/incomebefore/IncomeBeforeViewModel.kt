package com.cpu.quikdata.feature.createform.livelihoodsinfo.incomebefore

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.cpu.quikdata.data.livelihoodsinfo.incomebefore.IncomeBeforeRow
import javax.inject.Inject

class IncomeBeforeViewModel @Inject constructor (private val mRepository: IncomeBeforeRepository)
    : ViewModel() {

    val incomeBefore: LiveData<List<IncomeBeforeRow>>
        get() = mRepository.incomeBefore

    fun updateRow(incomeBeforeRow: IncomeBeforeRow) = mRepository.updateData(incomeBeforeRow)

    fun createRow() = mRepository.createData()

    fun deleteRow(row: IncomeBeforeRow) = mRepository.deleteData(row)
}

