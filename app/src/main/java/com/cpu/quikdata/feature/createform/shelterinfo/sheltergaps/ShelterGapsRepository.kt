package com.cpu.quikdata.feature.createform.shelterinfo.sheltergaps

import androidx.lifecycle.LiveData
import com.cpu.quikdata.base.BaseUpdateableRepository
import com.cpu.quikdata.data.AppDatabase
import com.cpu.quikdata.data.shelterinfo.sheltergaps.ShelterGaps
import com.cpu.quikdata.utils.runOnIoThread

class ShelterGapsRepository(private val mDatabase: AppDatabase, val formId: String) :
    BaseUpdateableRepository<ShelterGaps>() {

    private val mShelterGaps = mDatabase.shelterGapsDao().getByFormId(formId)

    val shelterGaps: LiveData<ShelterGaps>
        get() = mShelterGaps

    override fun updateData(data: ShelterGaps) {
        runOnIoThread {
            val oldShelterGapsInfo = mShelterGaps.value!!
            oldShelterGapsInfo.copyFrom(data)
            mDatabase.shelterGapsDao().update(oldShelterGapsInfo)
        }
    }
}