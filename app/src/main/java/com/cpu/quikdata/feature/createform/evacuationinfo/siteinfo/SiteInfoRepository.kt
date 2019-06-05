package com.cpu.quikdata.feature.createform.evacuationinfo.siteinfo

import android.app.Application
import androidx.lifecycle.LiveData
import com.cpu.quikdata.base.BaseRepository
import com.cpu.quikdata.data.evacuation.siteinfo.SiteInfo
import com.cpu.quikdata.utils.runOnIoThread

class SiteInfoRepository(application: Application, formId: String) :
    BaseRepository<SiteInfo>(application) {

    private val mSiteInfo = mDatabase.siteInfoDao().getByEvacuationId(formId)

    val siteInfo: LiveData<SiteInfo>
        get() = mSiteInfo

    override fun updateData(data: SiteInfo) {
        runOnIoThread {
            val oldSiteInfo = mSiteInfo.value!!
            oldSiteInfo.copyFrom(data)
            mDatabase.siteInfoDao().update(oldSiteInfo)
        }
    }
}