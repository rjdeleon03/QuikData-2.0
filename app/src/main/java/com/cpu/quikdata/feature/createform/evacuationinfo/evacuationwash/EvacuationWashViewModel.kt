package com.cpu.quikdata.feature.createform.evacuationinfo.evacuationwash

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.cpu.quikdata.data.evacuation.evacuationwash.EvacuationWash
import javax.inject.Inject

class EvacuationWashViewModel @Inject constructor(private val mRepository: EvacuationWashRepository)
    : ViewModel() {

    val evacuationWash: LiveData<EvacuationWash>
        get() = mRepository.evacuationWash

    fun updateEvacuationWash(evacuationWash: EvacuationWash) =
        mRepository.updateData(evacuationWash)
}
