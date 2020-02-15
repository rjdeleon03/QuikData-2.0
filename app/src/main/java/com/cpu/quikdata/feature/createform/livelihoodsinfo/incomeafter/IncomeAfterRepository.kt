package com.cpu.quikdata.feature.createform.livelihoodsinfo.incomeafter

import androidx.lifecycle.LiveData
import com.cpu.quikdata.base.BaseCreatableDataRepository
import com.cpu.quikdata.data.AppDatabase
import com.cpu.quikdata.data.livelihoodsinfo.incomeafter.IncomeAfterRow
import com.cpu.quikdata.utils.generateId
import com.cpu.quikdata.utils.getDateTimeNowInLong

class IncomeAfterRepository(private val mDatabase: AppDatabase, val formId: String) :
    BaseCreatableDataRepository<IncomeAfterRow>() {

    private val mIncomeAfter = mDatabase.incomeAfterRowDao().getByFormId(formId)

    val incomeAfter: LiveData<List<IncomeAfterRow>>
        get() = mIncomeAfter

    override suspend fun updateData(data: IncomeAfterRow) {
        mDatabase.incomeAfterRowDao().update(data)
    }

    override suspend fun createData(id: String) {
        val row = IncomeAfterRow(id = generateId(),
            dateCreated = getDateTimeNowInLong(),
            formId = formId)
        mDatabase.incomeAfterRowDao().insert(row)
    }

    override suspend fun deleteData(data: IncomeAfterRow) {
        mDatabase.incomeAfterRowDao().delete(data)
    }
}