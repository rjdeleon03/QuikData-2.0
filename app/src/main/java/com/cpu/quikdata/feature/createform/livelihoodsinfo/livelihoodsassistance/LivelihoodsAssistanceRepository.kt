package com.cpu.quikdata.feature.createform.livelihoodsinfo.livelihoodsassistance

import androidx.lifecycle.LiveData
import com.cpu.quikdata.base.BaseCreatableDataRepository
import com.cpu.quikdata.data.AppDatabase
import com.cpu.quikdata.data.livelihoodsinfo.livelihoodsassistance.LivelihoodsAssistanceRow
import com.cpu.quikdata.utils.generateId
import com.cpu.quikdata.utils.getDateNowInLong
import com.cpu.quikdata.utils.getDateTimeNowInLong
import com.cpu.quikdata.utils.runOnIoThread
import javax.inject.Inject

class LivelihoodsAssistanceRepository @Inject constructor(
    private val mDatabase: AppDatabase, private val mFormId: String)
    : BaseCreatableDataRepository<LivelihoodsAssistanceRow>() {

    private val mLivelihoodsAssistance = mDatabase.livelihoodsAssistanceRowDao().getByFormId(mFormId)

    val livelihoodsAssistance: LiveData<List<LivelihoodsAssistanceRow>>
        get() = mLivelihoodsAssistance

    override fun updateData(data: LivelihoodsAssistanceRow) {
        runOnIoThread {
            mDatabase.livelihoodsAssistanceRowDao().update(data)
        }
    }

    override fun createData(id: String) {
        runOnIoThread {
            val row = LivelihoodsAssistanceRow(id = generateId(),
                dateReceived = getDateNowInLong(),
                dateCreated = getDateTimeNowInLong(),
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