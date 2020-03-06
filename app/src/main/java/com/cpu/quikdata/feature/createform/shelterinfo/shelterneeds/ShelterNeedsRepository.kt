package com.cpu.quikdata.feature.createform.shelterinfo.shelterneeds

import androidx.lifecycle.LiveData
import com.cpu.quikdata.data.AppDatabase
import com.cpu.quikdata.data.shelterinfo.shelterneedsrow.ShelterNeedsRow
import com.cpu.quikdata.utils.runOnIoThread
import javax.inject.Inject

class ShelterNeedsRepository @Inject constructor(
    private val mDatabase: AppDatabase, private val mFormId: String) {

    private val mShelterNeeds = mDatabase.shelterNeedsRowDao().getByFormId(mFormId)

    val shelterNeeds: LiveData<List<ShelterNeedsRow>>
        get() = mShelterNeeds

    fun updateData(data: ShelterNeedsRow) {
        runOnIoThread {
            mDatabase.shelterNeedsRowDao().update(data)
        }
    }
}