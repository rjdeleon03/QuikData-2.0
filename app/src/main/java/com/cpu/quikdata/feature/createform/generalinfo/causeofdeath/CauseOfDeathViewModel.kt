package com.cpu.quikdata.feature.createform.generalinfo.causeofdeath

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.cpu.quikdata.data.generalinfo.causeofdeath.CauseOfDeathRow
import javax.inject.Inject

class CauseOfDeathViewModel @Inject constructor (private val mRepository: CauseOfDeathRepository)
    : ViewModel() {

    val casualties: LiveData<List<CauseOfDeathRow>>
        get() = mRepository.causesOfDeath

    fun updateRow(causesOfDeath: CauseOfDeathRow) = mRepository.updateData(causesOfDeath)

}
