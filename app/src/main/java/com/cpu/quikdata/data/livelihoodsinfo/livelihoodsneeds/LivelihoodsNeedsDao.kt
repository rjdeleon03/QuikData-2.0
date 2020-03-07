package com.cpu.quikdata.data.livelihoodsinfo.livelihoodsneeds

import androidx.lifecycle.LiveData
import androidx.room.*
import com.cpu.quikdata.base.BaseDao

@Dao
interface LivelihoodsNeedsDao : BaseDao<LivelihoodsNeeds> {

    @Query("SELECT * FROM livelihoods_needs WHERE formId = :formId")
    fun getByFormId(formId: String): LiveData<LivelihoodsNeeds>

    @Query("SELECT * FROM livelihoods_needs WHERE formId = :formId")
    suspend fun getByFormIdNonLive(formId: String): LivelihoodsNeeds
}