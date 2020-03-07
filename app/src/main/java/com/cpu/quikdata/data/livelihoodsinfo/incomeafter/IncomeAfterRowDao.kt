package com.cpu.quikdata.data.livelihoodsinfo.incomeafter

import androidx.lifecycle.LiveData
import androidx.room.*
import com.cpu.quikdata.base.BaseRowDao

@Dao
interface IncomeAfterRowDao : BaseRowDao<IncomeAfterRow> {

    @Query("SELECT * FROM income_after_row WHERE formId = :formId ORDER BY dateCreated")
    fun getByFormId(formId: String): LiveData<List<IncomeAfterRow>>

    @Query("SELECT * FROM income_after_row WHERE formId = :formId ORDER BY dateCreated")
    suspend fun getByFormIdNonLive(formId: String): List<IncomeAfterRow>
}