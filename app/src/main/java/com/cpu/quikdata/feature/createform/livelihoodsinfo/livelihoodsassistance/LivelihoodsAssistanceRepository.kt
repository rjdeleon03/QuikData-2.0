package com.cpu.quikdata.feature.createform.livelihoodsinfo.livelihoodsassistance

import androidx.lifecycle.LiveData
import com.cpu.quikdata.base.BaseCreatableDataRepository
import com.cpu.quikdata.data.AppDatabase
import com.cpu.quikdata.data.livelihoodsinfo.livelihoodsassistance.LivelihoodsAssistanceRow
import com.cpu.quikdata.utils.generateId
import com.cpu.quikdata.utils.getDateNowInLong
import com.cpu.quikdata.utils.getDateTimeNowInLong

class LivelihoodsAssistanceRepository(private val mDatabase: AppDatabase, val formId: String) :
    BaseCreatableDataRepository<LivelihoodsAssistanceRow>() {

    private val mLivelihoodsAssistance = mDatabase.livelihoodsAssistanceRowDao().getByFormId(formId)

    val livelihoodsAssistance: LiveData<List<LivelihoodsAssistanceRow>>
        get() = mLivelihoodsAssistance

    override suspend fun updateData(data: LivelihoodsAssistanceRow) {
        mDatabase.livelihoodsAssistanceRowDao().update(data)
    }

    override suspend fun createData(id: String) {
        val row = LivelihoodsAssistanceRow(id = generateId(),
            dateReceived = getDateNowInLong(),
            dateCreated = getDateTimeNowInLong(),
            formId = formId)
        mDatabase.livelihoodsAssistanceRowDao().insert(row)
    }

    override suspend fun deleteData(data: LivelihoodsAssistanceRow) {
        mDatabase.livelihoodsAssistanceRowDao().delete(data)
    }
}