package com.cpu.quikdata.feature.createform.shelterinfo.sheltergaps

import androidx.lifecycle.LiveData
import com.cpu.quikdata.data.AppDatabase
import com.cpu.quikdata.data.shelterinfo.sheltergaps.ShelterGaps
import com.cpu.quikdata.utils.runOnIoThread
import javax.inject.Inject

class ShelterGapsRepository @Inject constructor(
    private val mDatabase: AppDatabase, private val mFormId: String) {

    private val mShelterGaps = mDatabase.shelterGapsDao().getByFormId(mFormId)

    val shelterGaps: LiveData<ShelterGaps>
        get() = mShelterGaps

    fun updateData(data: ShelterGaps) {
        runOnIoThread {
            val oldShelterGapsInfo = mShelterGaps.value!!
            oldShelterGapsInfo.copyFrom(data)
            mDatabase.shelterGapsDao().update(oldShelterGapsInfo)
        }
    }
}