package com.cpu.quikdata.feature.createform.watersanitationinfo.washcoping

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.cpu.quikdata.data.watersanitationinfo.washcoping.WashCoping
import javax.inject.Inject

class WashCopingViewModel @Inject constructor(private val mRepository: WashCopingRepository)
    : ViewModel() {

    val washCoping : LiveData<WashCoping>
        get() = mRepository.washCoping

    fun updateWashCoping(washCoping: WashCoping) =
        mRepository.updateData(washCoping)
}
