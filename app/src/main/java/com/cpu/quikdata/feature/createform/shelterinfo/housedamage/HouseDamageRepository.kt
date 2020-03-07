package com.cpu.quikdata.feature.createform.shelterinfo.housedamage

import androidx.lifecycle.LiveData
import com.cpu.quikdata.data.AppDatabase
import com.cpu.quikdata.data.shelterinfo.housedamagerow.HouseDamageRow
import javax.inject.Inject

class HouseDamageRepository @Inject constructor(
    private val mDatabase: AppDatabase, formId: String
) {

    private val mHouseDamage = mDatabase.houseDamageRowDao().getByFormId(formId)

    val houseDamage: LiveData<List<HouseDamageRow>>
        get() = mHouseDamage

    suspend fun updateData(data: HouseDamageRow) {
        mDatabase.houseDamageRowDao().update(data)
    }
}