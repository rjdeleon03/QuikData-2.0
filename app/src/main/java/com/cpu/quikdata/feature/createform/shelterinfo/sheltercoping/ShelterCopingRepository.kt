package com.cpu.quikdata.feature.createform.shelterinfo.sheltercoping

import androidx.lifecycle.LiveData
import com.cpu.quikdata.base.BaseUpdateableRepository
import com.cpu.quikdata.data.AppDatabase
import com.cpu.quikdata.data.shelterinfo.sheltercoping.ShelterCoping

class ShelterCopingRepository(private val mDatabase: AppDatabase, val formId: String) :
    BaseUpdateableRepository<ShelterCoping>() {

    private val mShelterCoping = mDatabase.shelterCopingDao().getByFormId(formId)

    val shelterCoping: LiveData<ShelterCoping>
        get() = mShelterCoping

    override suspend fun updateData(data: ShelterCoping) {
        mShelterCoping.value?.let {
            it.copyFrom(data)
            mDatabase.shelterCopingDao().update(it)
        }
    }
}