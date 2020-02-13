package com.cpu.quikdata.feature.createform.shelterinfo.housedamage

import androidx.lifecycle.LiveData
import com.cpu.quikdata.base.BaseUpdateableRepository
import com.cpu.quikdata.data.AppDatabase
import com.cpu.quikdata.data.shelterinfo.housedamagerow.HouseDamageRow
import com.cpu.quikdata.utils.runOnIoThread

class HouseDamageRepository(private val mDatabase: AppDatabase, val formId: String) :
    BaseUpdateableRepository<HouseDamageRow>() {

    private val mHouseDamage = mDatabase.houseDamageRowDao().getByFormId(formId)

    val houseDamage: LiveData<List<HouseDamageRow>>
        get() = mHouseDamage

    override fun updateData(data: HouseDamageRow) {
        runOnIoThread {
            mDatabase.houseDamageRowDao().update(data)
        }
    }
}