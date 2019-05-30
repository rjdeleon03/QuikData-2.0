package com.cpu.quikdata.feature.createform.shelterinfo.housedamage

import android.app.Application
import androidx.lifecycle.LiveData
import com.cpu.quikdata.base.BaseRepository
import com.cpu.quikdata.data.shelterinfo.housedamagerow.HouseDamageRow
import com.cpu.quikdata.utils.runOnIoThread

class HouseDamageRepository(application: Application, formId: String) :
    BaseRepository<HouseDamageRow>(application) {

    private val mHouseDamage = mDatabase.houseDamageRowDao().getByFormId(formId)

    val houseDamage: LiveData<List<HouseDamageRow>>
        get() = mHouseDamage

    override fun updateData(data: HouseDamageRow) {
        runOnIoThread {
            mDatabase.houseDamageRowDao().update(data)
        }
    }
}