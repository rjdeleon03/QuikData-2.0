package com.cpu.quikdata.feature.createform.evacuationinfo.evacuationprotection

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.cpu.quikdata.data.evacuation.evacuationprotection.EvacuationProtection

class EvacuationProtectionViewModel(application: Application, evacuationId: String) :
    AndroidViewModel(application) {

    private val mRepository = EvacuationProtectionRepository(application, evacuationId)

    val evacuationProtection: LiveData<EvacuationProtection>
        get() = mRepository.evacuationProtection

    fun updateEvacuationProtection(evacuationProtection: EvacuationProtection) =
        mRepository.updateData(evacuationProtection)
}