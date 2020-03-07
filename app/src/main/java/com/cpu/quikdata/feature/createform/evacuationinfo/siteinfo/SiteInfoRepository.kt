package com.cpu.quikdata.feature.createform.evacuationinfo.siteinfo

import androidx.lifecycle.LiveData
import com.cpu.quikdata.data.AppDatabase
import com.cpu.quikdata.data.evacuation.siteinfo.SiteInfo
import com.cpu.quikdata.di.EvacuationId
import com.cpu.quikdata.utils.runOnIoThread
import javax.inject.Inject

class SiteInfoRepository @Inject constructor(private val mDatabase: AppDatabase,
                                             @EvacuationId evacuationId: String) {

    private val mSiteInfo = mDatabase.siteInfoDao().getByEvacuationId(evacuationId)

    val siteInfo: LiveData<SiteInfo>
        get() = mSiteInfo

    fun updateData(data: SiteInfo) {
        runOnIoThread {
            val oldSiteInfo = mSiteInfo.value!!
            oldSiteInfo.copyFrom(data)
            mDatabase.siteInfoDao().update(oldSiteInfo)
        }
    }
}