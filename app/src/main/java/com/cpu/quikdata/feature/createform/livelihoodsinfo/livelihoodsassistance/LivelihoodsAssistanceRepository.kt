package com.cpu.quikdata.feature.createform.livelihoodsinfo.livelihoodsassistance

import android.app.Application
import androidx.lifecycle.LiveData
import com.cpu.quikdata.base.BaseCreatableDataRepository
import com.cpu.quikdata.data.livelihoodsinfo.livelihoodsassistance.LivelihoodsAssistanceRow
import com.cpu.quikdata.utils.generateId
import com.cpu.quikdata.utils.runOnIoThread
import org.joda.time.LocalDate
import org.joda.time.LocalDateTime

class LivelihoodsAssistanceRepository(application: Application, formId: String) :
    BaseCreatableDataRepository<LivelihoodsAssistanceRow>(application) {

    private val mFormId = formId
    private val mLivelihoodsAssistance = mDatabase.livelihoodsAssistanceRowDao().getByFormId(formId)

    val livelihoodsAssistance: LiveData<List<LivelihoodsAssistanceRow>>
        get() = mLivelihoodsAssistance

    override fun updateData(data: LivelihoodsAssistanceRow) {
        runOnIoThread {
            mDatabase.livelihoodsAssistanceRowDao().update(data)
        }
    }

    override fun createData(id: String) {
        runOnIoThread {
            val dateTodayInMillis = LocalDate.now().toDateTimeAtStartOfDay().millis
            val row = LivelihoodsAssistanceRow(id = generateId(),
                dateReceived = dateTodayInMillis,
                dateCreated = LocalDateTime.now().toDateTime().millis,
                formId = mFormId)
            mDatabase.livelihoodsAssistanceRowDao().insert(row)
        }
    }

    override fun deleteData(data: LivelihoodsAssistanceRow) {
        runOnIoThread {
            mDatabase.livelihoodsAssistanceRowDao().delete(data)
        }
    }
}