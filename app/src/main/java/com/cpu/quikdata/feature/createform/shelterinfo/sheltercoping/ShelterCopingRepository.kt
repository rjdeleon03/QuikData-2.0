package com.cpu.quikdata.feature.createform.shelterinfo.sheltercoping

import androidx.lifecycle.LiveData
import com.cpu.quikdata.data.AppDatabase
import com.cpu.quikdata.data.shelterinfo.sheltercoping.ShelterCoping
import javax.inject.Inject

class ShelterCopingRepository @Inject constructor(
    private val mDatabase: AppDatabase, formId: String
) {

    private val mShelterCoping = mDatabase.shelterCopingDao().getByFormId(formId)

    val shelterCoping: LiveData<ShelterCoping>
        get() = mShelterCoping

    suspend fun updateData(data: ShelterCoping) {
        mShelterCoping.value?.apply {
            copyFrom(data)
            mDatabase.shelterCopingDao().update(this)
        }
    }
}