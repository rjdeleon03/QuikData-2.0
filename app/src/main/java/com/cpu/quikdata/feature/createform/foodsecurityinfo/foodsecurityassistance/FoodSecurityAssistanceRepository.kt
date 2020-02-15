package com.cpu.quikdata.feature.createform.foodsecurityinfo.foodsecurityassistance

import androidx.lifecycle.LiveData
import com.cpu.quikdata.base.BaseCreatableDataRepository
import com.cpu.quikdata.data.AppDatabase
import com.cpu.quikdata.data.foodsecurityinfo.foodsecurityassistance.FoodSecurityAssistanceRow
import com.cpu.quikdata.utils.generateId
import com.cpu.quikdata.utils.getDateNowInLong
import com.cpu.quikdata.utils.getDateTimeNowInLong

class FoodSecurityAssistanceRepository(private val mDatabase: AppDatabase, val formId: String) :
    BaseCreatableDataRepository<FoodSecurityAssistanceRow>() {

    private val mFoodSecurityAssistance = mDatabase.foodSecurityAssistanceRowDao().getByFormId(formId)

    val foodSecurityAssistance: LiveData<List<FoodSecurityAssistanceRow>>
        get() = mFoodSecurityAssistance

    override suspend fun updateData(data: FoodSecurityAssistanceRow) {
        mDatabase.foodSecurityAssistanceRowDao().update(data)
    }

    override suspend fun createData(id: String) {
        val row = FoodSecurityAssistanceRow(id = generateId(),
            dateReceived = getDateNowInLong(),
            dateCreated = getDateTimeNowInLong(),
            formId = formId)
        mDatabase.foodSecurityAssistanceRowDao().insert(row)
    }

    override suspend fun deleteData(data: FoodSecurityAssistanceRow) {
        mDatabase.foodSecurityAssistanceRowDao().delete(data)
    }
}