package com.cpu.quikdata.feature.createform.livelihoodsinfo.incomeafter

import android.app.Application
import androidx.lifecycle.LiveData
import com.cpu.quikdata.base.BaseCreatableDataRepository
import com.cpu.quikdata.data.livelihoodsinfo.incomeafter.IncomeAfterRow
import com.cpu.quikdata.utils.generateId
import com.cpu.quikdata.utils.runOnIoThread
import org.joda.time.LocalDateTime

class IncomeAfterRepository(application: Application, formId: String) :
    BaseCreatableDataRepository<IncomeAfterRow>(application) {

    private val mFormId = formId
    private val mIncomeAfter = mDatabase.incomeAfterRowDao().getByFormId(formId)

    val incomeAfter: LiveData<List<IncomeAfterRow>>
        get() = mIncomeAfter

    override fun updateData(data: IncomeAfterRow) {
        runOnIoThread {
            mDatabase.incomeAfterRowDao().update(data)
        }
    }

    override fun createData() {
        runOnIoThread {
            val row = IncomeAfterRow(id = generateId(),
                dateCreated = LocalDateTime.now().toDateTime().millis,
                formId = mFormId)
            mDatabase.incomeAfterRowDao().insert(row)
        }
    }
}