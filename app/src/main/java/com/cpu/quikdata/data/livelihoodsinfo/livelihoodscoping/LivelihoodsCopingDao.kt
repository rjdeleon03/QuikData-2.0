package com.cpu.quikdata.data.livelihoodsinfo.livelihoodscoping

import androidx.lifecycle.LiveData
import androidx.room.*
import com.cpu.quikdata.base.BaseDao

@Dao
interface LivelihoodsCopingDao : BaseDao<LivelihoodsCoping> {

    @Query("SELECT * FROM livelihoods_coping WHERE formId = :formId")
    fun getByFormId(formId: String): LiveData<LivelihoodsCoping>

    @Query("SELECT * FROM livelihoods_coping WHERE formId = :formId")
    fun getByFormIdNonLive(formId: String): LivelihoodsCoping
}