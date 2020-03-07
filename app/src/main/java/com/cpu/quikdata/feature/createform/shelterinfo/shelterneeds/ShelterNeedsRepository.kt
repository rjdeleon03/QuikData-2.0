package com.cpu.quikdata.feature.createform.shelterinfo.shelterneeds

import androidx.lifecycle.LiveData
import com.cpu.quikdata.data.AppDatabase
import com.cpu.quikdata.data.shelterinfo.shelterneedsrow.ShelterNeedsRow
import javax.inject.Inject

class ShelterNeedsRepository @Inject constructor(
    private val mDatabase: AppDatabase, formId: String
) {

    private val mShelterNeeds = mDatabase.shelterNeedsRowDao().getByFormId(formId)

    val shelterNeeds: LiveData<List<ShelterNeedsRow>>
        get() = mShelterNeeds

    suspend fun updateData(data: ShelterNeedsRow) {
        mDatabase.shelterNeedsRowDao().update(data)
    }
}