package com.cpu.quikdata.feature.createform.generalinfo.calamityinfo

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.cpu.quikdata.common.runOnIoThread
import com.cpu.quikdata.data.generalinfo.calamityinfo.CalamityInfo
import javax.inject.Inject

class CalamityInfoViewModel @Inject constructor (private val mRepository: CalamityInfoRepository)
    : ViewModel() {

    val calamityInfo: LiveData<CalamityInfo>
        get() = mRepository.calamityInfo

    fun updateCalamityInfo(calamityInfo: CalamityInfo) =
        runOnIoThread { mRepository.updateData(calamityInfo) }

}
