package com.cpu.quikdata.data.livelihoodsinfo.livelihoodsassistance

import androidx.lifecycle.LiveData
import androidx.room.*
import com.cpu.quikdata.base.BaseDao

@Dao
interface LivelihoodsAssistanceRowDao : BaseDao<LivelihoodsAssistanceRow> {

    @Query("SELECT * FROM livelihoods_assistance_row WHERE formId = :formId ORDER BY dateCreated")
    fun getByFormId(formId: String): LiveData<List<LivelihoodsAssistanceRow>>
}