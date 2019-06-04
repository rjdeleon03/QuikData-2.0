package com.cpu.quikdata.data.evacuation.siteinfo

import androidx.lifecycle.LiveData
import androidx.room.*
import com.cpu.quikdata.base.BaseDao

@Dao
interface SiteInfoDao : BaseDao<SiteInfo> {

    @Query("SELECT * FROM site_info WHERE evacuationId = :evacuationId")
    fun getByEvacuationId(evacuationId: String): LiveData<SiteInfo>
}