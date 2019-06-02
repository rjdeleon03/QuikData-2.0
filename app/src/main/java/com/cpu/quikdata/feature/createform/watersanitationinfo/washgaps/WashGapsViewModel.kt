package com.cpu.quikdata.feature.createform.watersanitationinfo.washgaps

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.cpu.quikdata.data.watersanitationinfo.washgaps.WashGaps

class WashGapsViewModel(application: Application, formId: String) :
    AndroidViewModel(application) {

    private val mRepository = WashGapsRepository(application, formId)

    val washGaps : LiveData<WashGaps>
        get() = mRepository.washGaps

    fun updateWashGaps(washGaps: WashGaps) =
        mRepository.updateData(washGaps)
}
