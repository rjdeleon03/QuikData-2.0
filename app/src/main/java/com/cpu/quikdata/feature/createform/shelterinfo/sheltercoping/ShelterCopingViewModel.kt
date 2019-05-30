package com.cpu.quikdata.feature.createform.shelterinfo.sheltercoping

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.cpu.quikdata.data.shelterinfo.sheltercoping.ShelterCoping


class ShelterCopingViewModel(application: Application, formId: String) :
    AndroidViewModel(application) {

    private val mRepository = ShelterCopingRepository(application, formId)

    val shelterCoping: LiveData<ShelterCoping>
        get() = mRepository.shelterCoping

    fun updateShelterCopingInfo(shelterCoping: ShelterCoping) =
        mRepository.updateData(shelterCoping)
}
