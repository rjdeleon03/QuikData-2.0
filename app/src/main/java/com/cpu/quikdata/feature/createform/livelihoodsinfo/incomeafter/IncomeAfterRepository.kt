package com.cpu.quikdata.feature.createform.livelihoodsinfo.incomeafter

import androidx.lifecycle.LiveData
import com.cpu.quikdata.base.BaseCreatableDataRepository
import com.cpu.quikdata.data.AppDatabase
import com.cpu.quikdata.data.livelihoodsinfo.incomeafter.IncomeAfterRow
import com.cpu.quikdata.utils.generateId
import com.cpu.quikdata.utils.getDateTimeNowInLong
import com.cpu.quikdata.utils.runOnIoThread
import javax.inject.Inject

class IncomeAfterRepository @Inject constructor(
    private val mDatabase: AppDatabase, private val mFormId: String) :
    BaseCreatableDataRepository<IncomeAfterRow>() {

    private val mIncomeAfter = mDatabase.incomeAfterRowDao().getByFormId(mFormId)

    val incomeAfter: LiveData<List<IncomeAfterRow>>
        get() = mIncomeAfter

    override fun updateData(data: IncomeAfterRow) {
        runOnIoThread {
            mDatabase.incomeAfterRowDao().update(data)
        }
    }

    override fun createData(id: String) {
        runOnIoThread {
            val row = IncomeAfterRow(id = generateId(),
                dateCreated = getDateTimeNowInLong(),
                formId = mFormId)
            mDatabase.incomeAfterRowDao().insert(row)
        }
    }

    override fun deleteData(data: IncomeAfterRow) {
        runOnIoThread {
            mDatabase.incomeAfterRowDao().delete(data)
        }
    }
}