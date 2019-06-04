package com.cpu.quikdata.data.livelihoodsinfo.incomebefore

import androidx.lifecycle.LiveData
import androidx.room.*
import com.cpu.quikdata.base.BaseDao

@Dao
interface IncomeBeforeRowDao : BaseDao<IncomeBeforeRow> {

    @Query("SELECT * FROM income_before_row WHERE formId = :formId ORDER BY dateCreated")
    fun getByFormId(formId: String): LiveData<List<IncomeBeforeRow>>
}