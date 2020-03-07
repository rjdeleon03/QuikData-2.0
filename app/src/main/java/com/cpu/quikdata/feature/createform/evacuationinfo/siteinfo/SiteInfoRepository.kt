package com.cpu.quikdata.feature.createform.evacuationinfo.siteinfo

import androidx.lifecycle.LiveData
import com.cpu.quikdata.data.AppDatabase
import com.cpu.quikdata.data.evacuation.siteinfo.SiteInfo
import com.cpu.quikdata.di.EvacuationId
import javax.inject.Inject

class SiteInfoRepository @Inject constructor(private val mDatabase: AppDatabase,
                                             @EvacuationId evacuationId: String) {

    private val mSiteInfo = mDatabase.siteInfoDao().getByEvacuationId(evacuationId)

    val siteInfo: LiveData<SiteInfo>
        get() = mSiteInfo

    suspend fun updateData(data: SiteInfo) {
        mSiteInfo.value?.apply {
            copyFrom(data)
            mDatabase.siteInfoDao().update(this)
        }
    }
}