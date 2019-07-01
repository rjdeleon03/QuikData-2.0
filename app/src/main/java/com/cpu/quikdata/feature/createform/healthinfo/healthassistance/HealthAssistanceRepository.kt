package com.cpu.quikdata.feature.createform.healthinfo.healthassistance

import android.app.Application
import androidx.lifecycle.LiveData
import com.cpu.quikdata.base.BaseCreatableDataRepository
import com.cpu.quikdata.data.health.healthassistance.HealthAssistanceRow
import com.cpu.quikdata.utils.generateId
import com.cpu.quikdata.utils.getDateNowInLong
import com.cpu.quikdata.utils.getDateTimeNowInLong
import com.cpu.quikdata.utils.runOnIoThread
import org.joda.time.LocalDate
import org.joda.time.LocalDateTime

class HealthAssistanceRepository(application: Application, formId: String) :
    BaseCreatableDataRepository<HealthAssistanceRow>(application) {

    private val mFormId = formId
    private val mHealthAssistance = mDatabase.healthAssistanceRowDao().getByFormId(formId)

    val healthAssistance: LiveData<List<HealthAssistanceRow>>
        get() = mHealthAssistance

    override fun updateData(data: HealthAssistanceRow) {
        runOnIoThread {
            mDatabase.healthAssistanceRowDao().update(data)
        }
    }

    override fun createData(id: String) {
        runOnIoThread {
            val row = HealthAssistanceRow(id = generateId(),
                dateReceived = getDateNowInLong(),
                dateCreated = getDateTimeNowInLong(),
                formId = mFormId)
            mDatabase.healthAssistanceRowDao().insert(row)
        }
    }

    override fun deleteData(data: HealthAssistanceRow) {
        runOnIoThread {
            mDatabase.healthAssistanceRowDao().delete(data)
        }
    }
}