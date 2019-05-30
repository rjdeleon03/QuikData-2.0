package com.cpu.quikdata.feature.createform.shelterinfo.shelterneeds

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.cpu.quikdata.data.shelterinfo.shelterneedsrow.ShelterNeedsRow

class ShelterNeedsViewModel(application: Application, formId: String) :
    AndroidViewModel(application) {

    private val mRepository = ShelterNeedsRepository(application, formId)

    val shelterNeeds: LiveData<List<ShelterNeedsRow>>
        get() = mRepository.shelterNeeds

    fun updateRow(shelterNeedsRow: ShelterNeedsRow) = mRepository.updateData(shelterNeedsRow)

}
