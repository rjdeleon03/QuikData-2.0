package com.cpu.quikdata.feature.createform.watersanitationinfo.washgaps

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.cpu.quikdata.data.watersanitationinfo.washgaps.WashGaps
import javax.inject.Inject

class WashGapsViewModel @Inject constructor(private val mRepository: WashGapsRepository)
    : ViewModel() {

    val washGaps : LiveData<WashGaps>
        get() = mRepository.washGaps

    fun updateWashGaps(washGaps: WashGaps) =
        mRepository.updateData(washGaps)
}
