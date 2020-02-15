package com.cpu.quikdata.feature.createform.shelterinfo.shelterneeds

import androidx.lifecycle.LiveData
import com.cpu.quikdata.base.BaseUpdateableRepository
import com.cpu.quikdata.data.AppDatabase
import com.cpu.quikdata.data.shelterinfo.shelterneedsrow.ShelterNeedsRow

class ShelterNeedsRepository(private val mDatabase: AppDatabase, val formId: String) :
    BaseUpdateableRepository<ShelterNeedsRow>() {

    private val mShelterNeeds = mDatabase.shelterNeedsRowDao().getByFormId(formId)

    val shelterNeeds: LiveData<List<ShelterNeedsRow>>
        get() = mShelterNeeds

    override suspend fun updateData(data: ShelterNeedsRow) {
        mDatabase.shelterNeedsRowDao().update(data)
    }
}