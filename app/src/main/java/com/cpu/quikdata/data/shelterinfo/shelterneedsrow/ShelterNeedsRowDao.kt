package com.cpu.quikdata.data.shelterinfo.shelterneedsrow

import androidx.lifecycle.LiveData
import androidx.room.*
import com.cpu.quikdata.base.BaseDao

@Dao
interface ShelterNeedsRowDao : BaseDao<ShelterNeedsRow> {

    @Query("SELECT * FROM shelter_needs_row WHERE formId = :formId ORDER BY type")
    fun getByFormId(formId: String): LiveData<List<ShelterNeedsRow>>
}