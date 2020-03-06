package com.cpu.quikdata.feature.createform.shelterinfo.housedamage

import androidx.lifecycle.LiveData
import com.cpu.quikdata.data.AppDatabase
import com.cpu.quikdata.data.shelterinfo.housedamagerow.HouseDamageRow
import com.cpu.quikdata.utils.runOnIoThread
import javax.inject.Inject

class HouseDamageRepository @Inject constructor(
    private val mDatabase: AppDatabase, private val mFormId: String) {

    private val mHouseDamage = mDatabase.houseDamageRowDao().getByFormId(mFormId)

    val houseDamage: LiveData<List<HouseDamageRow>>
        get() = mHouseDamage

    fun updateData(data: HouseDamageRow) {
        runOnIoThread {
            mDatabase.houseDamageRowDao().update(data)
        }
    }
}