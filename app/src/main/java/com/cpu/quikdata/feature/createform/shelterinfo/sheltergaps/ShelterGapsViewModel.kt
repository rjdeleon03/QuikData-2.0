package com.cpu.quikdata.feature.createform.shelterinfo.sheltergaps

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.cpu.quikdata.data.shelterinfo.sheltergaps.ShelterGaps

class ShelterGapsViewModel(application: Application, formId: String) :
    AndroidViewModel(application) {

    private val mRepository = ShelterGapsRepository(application, formId)

    val shelterGaps: LiveData<ShelterGaps>
        get() = mRepository.shelterGaps

    fun updateShelterGaps(shelterGaps: ShelterGaps) =
        mRepository.updateData(shelterGaps)
}
