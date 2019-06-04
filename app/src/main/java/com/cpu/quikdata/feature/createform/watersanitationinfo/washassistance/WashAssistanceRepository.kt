package com.cpu.quikdata.feature.createform.watersanitationinfo.washassistance

import android.app.Application
import androidx.lifecycle.LiveData
import com.cpu.quikdata.base.BaseCreatableDataRepository
import com.cpu.quikdata.data.watersanitationinfo.washassistance.WashAssistanceRow
import com.cpu.quikdata.utils.generateId
import com.cpu.quikdata.utils.runOnIoThread
import org.joda.time.LocalDate
import org.joda.time.LocalDateTime

class WashAssistanceRepository(application: Application, formId: String) :
    BaseCreatableDataRepository<WashAssistanceRow>(application) {

    private val mFormId = formId
    private val mWashAssistance = mDatabase.washAssistanceRowDao().getByFormId(formId)

    val washAssistance: LiveData<List<WashAssistanceRow>>
        get() = mWashAssistance

    override fun updateData(data: WashAssistanceRow) {
        runOnIoThread {
            mDatabase.washAssistanceRowDao().update(data)
        }
    }

    override fun createData() {
        runOnIoThread {
            val dateTodayInMillis = LocalDate.now().toDateTimeAtStartOfDay().millis
            val row = WashAssistanceRow(id = generateId(),
                dateReceived = dateTodayInMillis,
                dateCreated = LocalDateTime.now().toDateTime().millis,
                formId = mFormId)
            mDatabase.washAssistanceRowDao().insert(row)
        }
    }
}