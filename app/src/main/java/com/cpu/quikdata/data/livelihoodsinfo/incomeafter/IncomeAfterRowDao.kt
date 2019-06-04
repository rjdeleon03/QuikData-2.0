package com.cpu.quikdata.data.livelihoodsinfo.incomeafter

import androidx.lifecycle.LiveData
import androidx.room.*
import com.cpu.quikdata.base.BaseDao

@Dao
interface IncomeAfterRowDao : BaseDao<IncomeAfterRow> {

    @Query("SELECT * FROM income_after_row WHERE formId = :formId ORDER BY dateCreated")
    fun getByFormId(formId: String): LiveData<List<IncomeAfterRow>>
}