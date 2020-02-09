package com.cpu.quikdata.feature.createform.generalinfo.causeofdeath

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.cpu.quikdata.data.generalinfo.causeofdeath.CauseOfDeathRow
import com.cpu.quikdata.feature.QuikDataApp

class CauseOfDeathViewModel(application: Application, formId: String) :
    AndroidViewModel(application) {

    private val mRepository = CauseOfDeathRepository(application as QuikDataApp, formId)

    val casualties: LiveData<List<CauseOfDeathRow>>
        get() = mRepository.causesOfDeath

    fun updateRow(causesOfDeath: CauseOfDeathRow) = mRepository.updateData(causesOfDeath)

}
