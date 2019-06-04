package com.cpu.quikdata.feature.createform.livelihoodsinfo.incomebefore

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.cpu.quikdata.data.livelihoodsinfo.incomebefore.IncomeBeforeRow

class IncomeBeforeViewModel(application: Application, formId: String) :
    AndroidViewModel(application) {

    private val mRepository = IncomeBeforeRepository(application, formId)

    val incomeBefore: LiveData<List<IncomeBeforeRow>>
        get() = mRepository.incomeBefore

    fun updateRow(incomeBeforeRow: IncomeBeforeRow) = mRepository.updateData(incomeBeforeRow)

    fun createRow() = mRepository.createData()
}

