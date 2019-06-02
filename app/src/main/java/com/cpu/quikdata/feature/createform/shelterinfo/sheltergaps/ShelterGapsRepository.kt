package com.cpu.quikdata.feature.createform.shelterinfo.sheltergaps

import android.app.Application
import androidx.lifecycle.LiveData
import com.cpu.quikdata.base.BaseRepository
import com.cpu.quikdata.data.shelterinfo.sheltergaps.ShelterGaps
import com.cpu.quikdata.utils.runOnIoThread

class ShelterGapsRepository(application: Application, formId: String) :
    BaseRepository<ShelterGaps>(application) {

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