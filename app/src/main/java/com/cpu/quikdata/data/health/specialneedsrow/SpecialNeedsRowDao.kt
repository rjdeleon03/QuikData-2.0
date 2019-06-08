package com.cpu.quikdata.data.health.specialneedsrow

import androidx.lifecycle.LiveData
import androidx.room.*
import com.cpu.quikdata.base.BaseRowDao

@Dao
interface SpecialNeedsRowDao : BaseRowDao<SpecialNeedsRow> {

    @Query("SELECT * FROM special_needs_row WHERE formId = :formId ORDER BY type")
    fun getByFormId(formId: String): LiveData<List<SpecialNeedsRow>>
}