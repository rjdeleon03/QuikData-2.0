package com.cpu.quikdata.feature.createform.shelterinfo.shelterassistance

import androidx.lifecycle.LiveData
import com.cpu.quikdata.base.BaseCreatableDataRepository
import com.cpu.quikdata.data.AppDatabase
import com.cpu.quikdata.data.shelterinfo.shelterassistance.ShelterAssistanceRow
import com.cpu.quikdata.utils.generateId
import com.cpu.quikdata.utils.getDateNowInLong
import com.cpu.quikdata.utils.getDateTimeNowInLong

class ShelterAssistanceRepository(private val mDatabase: AppDatabase, val formId: String) :
    BaseCreatableDataRepository<ShelterAssistanceRow>() {

    private val mShelterAssistance = mDatabase.shelterAssistanceRowDao().getByFormId(formId)

    val shelterAssistance: LiveData<List<ShelterAssistanceRow>>
        get() = mShelterAssistance

    override suspend fun updateData(data: ShelterAssistanceRow) {
        mDatabase.shelterAssistanceRowDao().update(data)
    }

    override suspend fun createData(id: String) {
        val row = ShelterAssistanceRow(id = generateId(),
            dateReceived = getDateNowInLong(),
            dateCreated = getDateTimeNowInLong(),
            formId = formId)
        mDatabase.shelterAssistanceRowDao().insert(row)
    }

    override suspend fun deleteData(data: ShelterAssistanceRow) {
        mDatabase.shelterAssistanceRowDao().delete(data)
    }
}