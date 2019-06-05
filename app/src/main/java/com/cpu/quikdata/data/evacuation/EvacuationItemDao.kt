package com.cpu.quikdata.data.evacuation

import androidx.lifecycle.LiveData
import androidx.room.*
import com.cpu.quikdata.base.BaseDao

@Dao
interface EvacuationItemDao : BaseDao<EvacuationItem> {

    @Transaction
    @Query("SELECT * FROM evacuation_item WHERE formId = :formId")
    fun getByFormId(formId: String): LiveData<List<EvacuationItemDetails>>
}