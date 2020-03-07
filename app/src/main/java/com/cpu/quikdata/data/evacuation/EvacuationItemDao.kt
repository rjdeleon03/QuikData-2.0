package com.cpu.quikdata.data.evacuation

import androidx.lifecycle.LiveData
import androidx.room.*
import com.cpu.quikdata.base.BaseRowDao

@Dao
interface EvacuationItemDao : BaseRowDao<EvacuationItem> {

    @Query("SELECT * FROM evacuation_item WHERE formId = :formId ORDER BY dateCreated")
    fun getByFormId(formId: String): LiveData<List<EvacuationItem>>

    @Transaction
    @Query("SELECT * FROM evacuation_item WHERE formId = :formId ORDER BY dateCreated")
    suspend fun getByFormIdNonLive(formId: String): List<EvacuationComplete>

    @Transaction
    @Query("SELECT * FROM evacuation_item WHERE formId = :formId")
    suspend fun getByFormIdForDisplay(formId: String): LiveData<List<EvacuationItemDetails>>
}