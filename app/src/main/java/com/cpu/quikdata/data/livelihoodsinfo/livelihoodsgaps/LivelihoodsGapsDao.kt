package com.cpu.quikdata.data.livelihoodsinfo.livelihoodsgaps

import androidx.lifecycle.LiveData
import androidx.room.*
import com.cpu.quikdata.base.BaseDao

@Dao
interface LivelihoodsGapsDao : BaseDao<LivelihoodsGaps> {

    @Query("SELECT * FROM livelihoods_gaps WHERE formId = :formId")
    fun getByFormId(formId: String): LiveData<LivelihoodsGaps>

    @Query("SELECT * FROM livelihoods_gaps WHERE formId = :formId")
    fun getByFormIdNonLive(formId: String): LivelihoodsGaps
}