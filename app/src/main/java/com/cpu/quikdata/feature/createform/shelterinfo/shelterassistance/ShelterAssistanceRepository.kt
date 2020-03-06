package com.cpu.quikdata.feature.createform.shelterinfo.shelterassistance

import androidx.lifecycle.LiveData
import com.cpu.quikdata.base.BaseCreatableDataRepository
import com.cpu.quikdata.data.AppDatabase
import com.cpu.quikdata.data.shelterinfo.shelterassistance.ShelterAssistanceRow
import com.cpu.quikdata.utils.generateId
import com.cpu.quikdata.utils.getDateNowInLong
import com.cpu.quikdata.utils.getDateTimeNowInLong
import com.cpu.quikdata.utils.runOnIoThread
import javax.inject.Inject

class ShelterAssistanceRepository @Inject constructor(
    private val mDatabase: AppDatabase, private val mFormId: String)
    : BaseCreatableDataRepository<ShelterAssistanceRow>() {

    private val mShelterAssistance = mDatabase.shelterAssistanceRowDao().getByFormId(mFormId)

    val shelterAssistance: LiveData<List<ShelterAssistanceRow>>
        get() = mShelterAssistance

    override fun updateData(data: ShelterAssistanceRow) {
        runOnIoThread {
            mDatabase.shelterAssistanceRowDao().update(data)
        }
    }

    override fun createData(id: String) {
        runOnIoThread {
            val row = ShelterAssistanceRow(id = generateId(),
                dateReceived = getDateNowInLong(),
                dateCreated = getDateTimeNowInLong(),
                formId = mFormId)
            mDatabase.shelterAssistanceRowDao().insert(row)
        }
    }

    override fun deleteData(data: ShelterAssistanceRow) {
        runOnIoThread {
            mDatabase.shelterAssistanceRowDao().delete(data)
        }
    }
}