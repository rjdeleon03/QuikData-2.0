package com.cpu.quikdata.feature.createform.foodsecurityinfo.foodsecurityassistance

import androidx.lifecycle.LiveData
import com.cpu.quikdata.base.BaseCreatableDataRepository
import com.cpu.quikdata.data.AppDatabase
import com.cpu.quikdata.data.foodsecurityinfo.foodsecurityassistance.FoodSecurityAssistanceRow
import com.cpu.quikdata.utils.generateId
import com.cpu.quikdata.utils.getDateNowInLong
import com.cpu.quikdata.utils.getDateTimeNowInLong
import javax.inject.Inject

class FoodSecurityAssistanceRepository @Inject constructor(
    private val mDatabase: AppDatabase, private val mFormId: String
) :
    BaseCreatableDataRepository<FoodSecurityAssistanceRow>() {

    private val mFoodSecurityAssistance =
        mDatabase.foodSecurityAssistanceRowDao().getByFormId(mFormId)

    val foodSecurityAssistance: LiveData<List<FoodSecurityAssistanceRow>>
        get() = mFoodSecurityAssistance

    override suspend fun updateData(data: FoodSecurityAssistanceRow) {
        mDatabase.foodSecurityAssistanceRowDao().update(data)
    }

    override suspend fun createData(id: String) {
        val row = FoodSecurityAssistanceRow(
            id = generateId(),
            dateReceived = getDateNowInLong(),
            dateCreated = getDateTimeNowInLong(),
            formId = mFormId
        )
        mDatabase.foodSecurityAssistanceRowDao().insert(row)
    }

    override suspend fun deleteData(data: FoodSecurityAssistanceRow) {
        mDatabase.foodSecurityAssistanceRowDao().delete(data)
    }
}