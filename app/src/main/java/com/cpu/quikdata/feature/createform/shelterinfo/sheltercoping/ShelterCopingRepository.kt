package com.cpu.quikdata.feature.createform.shelterinfo.sheltercoping

import androidx.lifecycle.LiveData
import com.cpu.quikdata.base.BaseUpdateableRepository
import com.cpu.quikdata.data.AppDatabase
import com.cpu.quikdata.data.shelterinfo.sheltercoping.ShelterCoping
import com.cpu.quikdata.utils.runOnIoThread

class ShelterCopingRepository(private val mDatabase: AppDatabase, val formId: String) :
    BaseUpdateableRepository<ShelterCoping>() {

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