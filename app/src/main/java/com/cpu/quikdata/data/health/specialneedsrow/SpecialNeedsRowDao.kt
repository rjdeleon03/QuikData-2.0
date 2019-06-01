package com.cpu.quikdata.data.health.specialneedsrow

import androidx.lifecycle.LiveData
import androidx.room.*
import com.cpu.quikdata.base.BaseDao

@Dao
interface SpecialNeedsRowDao : BaseDao<SpecialNeedsRow> {

    @Query("SELECT * FROM special_needs_row WHERE formId = :formId ORDER BY type")
    fun getByFormId(formId: String): LiveData<List<SpecialNeedsRow>>
}