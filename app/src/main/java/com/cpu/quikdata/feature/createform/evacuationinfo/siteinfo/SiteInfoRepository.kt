package com.cpu.quikdata.feature.createform.evacuationinfo.siteinfo

import androidx.lifecycle.LiveData
import com.cpu.quikdata.base.BaseUpdateableRepository
import com.cpu.quikdata.data.AppDatabase
import com.cpu.quikdata.data.evacuation.siteinfo.SiteInfo

class SiteInfoRepository(private val mDatabase: AppDatabase, val formId: String) :
    BaseUpdateableRepository<SiteInfo>() {

    private val mSiteInfo = mDatabase.siteInfoDao().getByEvacuationId(formId)

    val siteInfo: LiveData<SiteInfo>
        get() = mSiteInfo

    override suspend fun updateData(data: SiteInfo) {
        mSiteInfo.value?.let {
            it.copyFrom(data)
            mDatabase.siteInfoDao().update(it)
        }
    }
}