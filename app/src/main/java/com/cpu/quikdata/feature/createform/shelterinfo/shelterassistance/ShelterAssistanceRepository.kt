package com.cpu.quikdata.feature.createform.shelterinfo.shelterassistance

import android.app.Application
import androidx.lifecycle.LiveData
import com.cpu.quikdata.base.BaseRepository
import com.cpu.quikdata.data.shelterinfo.shelterassistance.ShelterAssistanceRow
import com.cpu.quikdata.utils.generateId
import com.cpu.quikdata.utils.runOnIoThread
import org.joda.time.LocalDate
import org.joda.time.LocalDateTime

class ShelterAssistanceRepository(application: Application, formId: String) :
    BaseRepository<ShelterAssistanceRow>(application) {

    private val mFormId = formId
    private val mShelterAssistance = mDatabase.shelterAssistanceRowDao().getByFormId(formId)

    val shelterAssistance: LiveData<List<ShelterAssistanceRow>>
        get() = mShelterAssistance

    override fun updateData(data: ShelterAssistanceRow) {
        runOnIoThread {
            mDatabase.shelterAssistanceRowDao().update(data)
        }
    }

    fun createData() {
        runOnIoThread {
            val dateTodayInMillis = LocalDate.now().toDateTimeAtStartOfDay().millis
            val row = ShelterAssistanceRow(id = generateId(),
                dateReceived = dateTodayInMillis,
                dateCreated = LocalDateTime.now().toDateTime().millis,
                formId = mFormId)
            mDatabase.shelterAssistanceRowDao().insert(row)
        }
    }
}