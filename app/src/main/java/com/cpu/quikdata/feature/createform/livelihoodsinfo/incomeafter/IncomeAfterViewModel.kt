package com.cpu.quikdata.feature.createform.livelihoodsinfo.incomeafter

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.cpu.quikdata.common.runOnIoThread
import com.cpu.quikdata.data.livelihoodsinfo.incomeafter.IncomeAfterRow
import javax.inject.Inject

class IncomeAfterViewModel @Inject constructor (private val mRepository: IncomeAfterRepository)
    : ViewModel() {

    val incomeAfter: LiveData<List<IncomeAfterRow>>
        get() = mRepository.incomeAfter

    fun updateRow(incomeAfterRow: IncomeAfterRow) =
        runOnIoThread { mRepository.updateData(incomeAfterRow) }

    fun createRow() =
        runOnIoThread { mRepository.createData() }

    fun deleteRow(row: IncomeAfterRow) =
        runOnIoThread { mRepository.deleteData(row) }
}