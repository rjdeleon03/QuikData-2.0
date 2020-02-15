package com.cpu.quikdata.feature.createform.healthinfo.healthassistance

import androidx.lifecycle.LiveData
import com.cpu.quikdata.base.BaseCreatableDataRepository
import com.cpu.quikdata.data.AppDatabase
import com.cpu.quikdata.data.health.healthassistance.HealthAssistanceRow
import com.cpu.quikdata.utils.generateId
import com.cpu.quikdata.utils.getDateNowInLong
import com.cpu.quikdata.utils.getDateTimeNowInLong

class HealthAssistanceRepository(private val mDatabase: AppDatabase, val formId: String) :
    BaseCreatableDataRepository<HealthAssistanceRow>() {

    private val mHealthAssistance = mDatabase.healthAssistanceRowDao().getByFormId(formId)

    val healthAssistance: LiveData<List<HealthAssistanceRow>>
        get() = mHealthAssistance

    override suspend fun updateData(data: HealthAssistanceRow) {
        mDatabase.healthAssistanceRowDao().update(data)
    }

    override suspend fun createData(id: String) {
        val row = HealthAssistanceRow(id = generateId(),
            dateReceived = getDateNowInLong(),
            dateCreated = getDateTimeNowInLong(),
            formId = formId)
        mDatabase.healthAssistanceRowDao().insert(row)
    }

    override suspend fun deleteData(data: HealthAssistanceRow) {
        mDatabase.healthAssistanceRowDao().delete(data)
    }
}