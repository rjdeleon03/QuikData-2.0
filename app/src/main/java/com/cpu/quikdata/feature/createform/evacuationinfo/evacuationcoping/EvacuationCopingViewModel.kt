package com.cpu.quikdata.feature.createform.evacuationinfo.evacuationcoping

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.cpu.quikdata.data.evacuation.evacuationcoping.EvacuationCoping

class EvacuationCopingViewModel(application: Application, evacuationId: String) :
    AndroidViewModel(application) {

    private val mRepository = EvacuationCopingRepository(application, evacuationId)

    val evacuationCoping: LiveData<EvacuationCoping>
        get() = mRepository.evacuationCoping

    fun updateEvacuationCoping(evacuationCoping: EvacuationCoping) =
        mRepository.updateData(evacuationCoping)
}

