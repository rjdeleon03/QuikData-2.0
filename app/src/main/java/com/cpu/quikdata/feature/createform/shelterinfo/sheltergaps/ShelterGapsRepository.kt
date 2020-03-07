package com.cpu.quikdata.feature.createform.shelterinfo.sheltergaps

import androidx.lifecycle.LiveData
import com.cpu.quikdata.data.AppDatabase
import com.cpu.quikdata.data.shelterinfo.sheltergaps.ShelterGaps
import javax.inject.Inject

class ShelterGapsRepository @Inject constructor(
    private val mDatabase: AppDatabase, formId: String
) {

    private val mShelterGaps = mDatabase.shelterGapsDao().getByFormId(formId)

    val shelterGaps: LiveData<ShelterGaps>
        get() = mShelterGaps

    suspend fun updateData(data: ShelterGaps) {
        mShelterGaps.value?.apply {
            copyFrom(data)
            mDatabase.shelterGapsDao().update(this)
        }
    }
}