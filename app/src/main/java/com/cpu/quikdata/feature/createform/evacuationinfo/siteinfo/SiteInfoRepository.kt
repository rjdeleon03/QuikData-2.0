package com.cpu.quikdata.feature.createform.evacuationinfo.siteinfo

import androidx.lifecycle.LiveData
import com.cpu.quikdata.base.BaseUpdateableRepository
import com.cpu.quikdata.data.AppDatabase
import com.cpu.quikdata.data.evacuation.siteinfo.SiteInfo
import com.cpu.quikdata.utils.runOnIoThread

class SiteInfoRepository(private val mDatabase: AppDatabase, val formId: String) :
    BaseUpdateableRepository<SiteInfo>() {

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