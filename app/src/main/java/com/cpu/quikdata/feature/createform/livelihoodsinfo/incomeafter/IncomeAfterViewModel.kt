package com.cpu.quikdata.feature.createform.livelihoodsinfo.incomeafter

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.cpu.quikdata.data.livelihoodsinfo.incomeafter.IncomeAfterRow

class IncomeAfterViewModel(application: Application, formId: String) :
    AndroidViewModel(application) {

    private val mRepository = IncomeAfterRepository(application, formId)

    val incomeAfter: LiveData<List<IncomeAfterRow>>
        get() = mRepository.incomeAfter

    fun updateRow(incomeAfterRow: IncomeAfterRow) = mRepository.updateData(incomeAfterRow)

    fun createRow() = mRepository.createData()
}