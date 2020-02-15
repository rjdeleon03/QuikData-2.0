package com.cpu.quikdata.feature.createform.evacuationinfo.siteinfo

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.cpu.quikdata.common.runOnIoThread
import com.cpu.quikdata.data.evacuation.siteinfo.SiteInfo
import javax.inject.Inject

class SiteInfoViewModel @Inject constructor(private val mRepository: SiteInfoRepository)
    : ViewModel() {

    val siteInfo: LiveData<SiteInfo>
        get() = mRepository.siteInfo

    fun updateSiteInfo(siteInfo: SiteInfo) =
        runOnIoThread { mRepository.updateData(siteInfo) }
}
