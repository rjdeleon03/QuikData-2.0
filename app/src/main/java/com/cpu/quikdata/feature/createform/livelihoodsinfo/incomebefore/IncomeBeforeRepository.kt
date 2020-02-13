package com.cpu.quikdata.feature.createform.livelihoodsinfo.incomebefore

import androidx.lifecycle.LiveData
import com.cpu.quikdata.base.BaseCreatableDataRepository
import com.cpu.quikdata.data.AppDatabase
import com.cpu.quikdata.data.livelihoodsinfo.incomebefore.IncomeBeforeRow
import com.cpu.quikdata.utils.generateId
import com.cpu.quikdata.utils.getDateTimeNowInLong
import com.cpu.quikdata.utils.runOnIoThread

class IncomeBeforeRepository(private val mDatabase: AppDatabase, val formId: String) :
    BaseCreatableDataRepository<IncomeBeforeRow>() {

    private val mIncomeBefore = mDatabase.incomeBeforeRowDao().getByFormId(formId)

    val incomeBefore: LiveData<List<IncomeBeforeRow>>
        get() = mIncomeBefore

    override fun updateData(data: IncomeBeforeRow) {
        runOnIoThread {
            mDatabase.incomeBeforeRowDao().update(data)
        }
    }

    override fun createData(id: String) {
        runOnIoThread {
            val row = IncomeBeforeRow(id = generateId(),
                dateCreated = getDateTimeNowInLong(),
                formId = formId)
            mDatabase.incomeBeforeRowDao().insert(row)
        }
    }

    override fun deleteData(data: IncomeBeforeRow) {
        runOnIoThread {
            mDatabase.incomeBeforeRowDao().delete(data)
        }
    }
}