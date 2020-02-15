package com.cpu.quikdata.feature.createform.watersanitationinfo.washassistance

import androidx.lifecycle.LiveData
import com.cpu.quikdata.base.BaseCreatableDataRepository
import com.cpu.quikdata.data.AppDatabase
import com.cpu.quikdata.data.watersanitationinfo.washassistance.WashAssistanceRow
import com.cpu.quikdata.utils.generateId
import com.cpu.quikdata.utils.getDateNowInLong
import com.cpu.quikdata.utils.getDateTimeNowInLong
import com.cpu.quikdata.utils.runOnIoThread

class WashAssistanceRepository(private val mDatabase: AppDatabase, val formId: String) :
    BaseCreatableDataRepository<WashAssistanceRow>() {

    private val mWashAssistance = mDatabase.washAssistanceRowDao().getByFormId(formId)

    val washAssistance: LiveData<List<WashAssistanceRow>>
        get() = mWashAssistance

    override fun updateData(data: WashAssistanceRow) {
        runOnIoThread {
            mDatabase.washAssistanceRowDao().update(data)
        }
    }

    override fun createData(id: String) {
        runOnIoThread {
            val row = WashAssistanceRow(id = generateId(),
                dateReceived = getDateNowInLong(),
                dateCreated = getDateTimeNowInLong(),
                formId = formId)
            mDatabase.washAssistanceRowDao().insert(row)
        }
    }

    override fun deleteData(data: WashAssistanceRow) {
        runOnIoThread {
            mDatabase.washAssistanceRowDao().delete(data)
        }
    }
}