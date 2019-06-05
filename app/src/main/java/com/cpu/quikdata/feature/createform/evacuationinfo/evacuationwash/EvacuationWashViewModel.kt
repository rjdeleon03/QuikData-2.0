package com.cpu.quikdata.feature.createform.evacuationinfo.evacuationwash

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.cpu.quikdata.data.evacuation.evacuationwash.EvacuationWash

class EvacuationWashViewModel(application: Application, evacuationId: String) :
    AndroidViewModel(application) {

    private val mRepository = EvacuationWashRepository(application, evacuationId)

    val evacuationWash: LiveData<EvacuationWash>
        get() = mRepository.evacuationWash

    fun updateEvacuationWash(evacuationWash: EvacuationWash) =
        mRepository.updateData(evacuationWash)
}
