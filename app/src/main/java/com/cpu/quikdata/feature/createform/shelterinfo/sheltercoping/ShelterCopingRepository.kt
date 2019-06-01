package com.cpu.quikdata.feature.createform.shelterinfo.sheltercoping

import android.app.Application
import androidx.lifecycle.LiveData
import com.cpu.quikdata.base.BaseRepository
import com.cpu.quikdata.data.shelterinfo.sheltercoping.ShelterCoping
import com.cpu.quikdata.utils.runOnIoThread

class ShelterCopingRepository(application: Application, formId: String) :
    BaseRepository<ShelterCoping>(application) {

    private val mShelterCoping = mDatabase.shelterCopingDao().getByFormId(formId)

    val shelterCoping: LiveData<ShelterCoping>
        get() = mShelterCoping

    override fun updateData(data: ShelterCoping) {
        runOnIoThread {
            val oldShelterCopingInfo = mShelterCoping.value!!
            oldShelterCopingInfo.copyFrom(data)
            mDatabase.shelterCopingDao().update(oldShelterCopingInfo)
        }
    }
}