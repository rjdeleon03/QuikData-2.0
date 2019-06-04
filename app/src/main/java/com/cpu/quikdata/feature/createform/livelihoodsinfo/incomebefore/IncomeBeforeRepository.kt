package com.cpu.quikdata.feature.createform.livelihoodsinfo.incomebefore

import android.app.Application
import androidx.lifecycle.LiveData
import com.cpu.quikdata.base.BaseCreatableDataRepository
import com.cpu.quikdata.data.livelihoodsinfo.incomebefore.IncomeBeforeRow
import com.cpu.quikdata.utils.generateId
import com.cpu.quikdata.utils.runOnIoThread
import org.joda.time.LocalDateTime

class IncomeBeforeRepository(application: Application, formId: String) :
    BaseCreatableDataRepository<IncomeBeforeRow>(application) {

    private val mFormId = formId
    private val mIncomeBefore = mDatabase.incomeBeforeRowDao().getByFormId(formId)

    val incomeBefore: LiveData<List<IncomeBeforeRow>>
        get() = mIncomeBefore

    override fun updateData(data: IncomeBeforeRow) {
        runOnIoThread {
            mDatabase.incomeBeforeRowDao().update(data)
        }
    }

    override fun createData() {
        runOnIoThread {
            val row = IncomeBeforeRow(id = generateId(),
                dateCreated = LocalDateTime.now().toDateTime().millis,
                formId = mFormId)
            mDatabase.incomeBeforeRowDao().insert(row)
        }
    }
}