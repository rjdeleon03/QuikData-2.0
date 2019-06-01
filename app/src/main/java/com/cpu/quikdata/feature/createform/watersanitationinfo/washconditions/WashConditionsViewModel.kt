package com.cpu.quikdata.feature.createform.watersanitationinfo.washconditions

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.cpu.quikdata.data.watersanitationinfo.washconditions.WashConditions

class WashConditionsViewModel(application: Application, formId: String) :
    AndroidViewModel(application) {

    private val mRepository = WashConditionsRepository(application, formId)

    val washConditions : LiveData<WashConditions>
        get() = mRepository.washConditions

    fun updateWashConditions(washConditions: WashConditions) =
        mRepository.updateData(washConditions)
}
