package com.cpu.quikdata.feature.createform.evacuationinfo.siteinfo

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.cpu.quikdata.data.evacuation.siteinfo.SiteInfo

class SiteInfoViewModel(application: Application, evacuationId: String) : AndroidViewModel(application) {

    private val mRepository = SiteInfoRepository(application, evacuationId)

    val siteInfo: LiveData<SiteInfo>
        get() = mRepository.siteInfo

    fun updateSiteInfo(siteInfo: SiteInfo) = mRepository.updateData(siteInfo)
}
