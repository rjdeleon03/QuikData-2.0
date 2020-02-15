package com.cpu.quikdata.feature.createform.healthinfo.diseases

import androidx.lifecycle.LiveData
import com.cpu.quikdata.base.BaseUpdateableRepository
import com.cpu.quikdata.data.AppDatabase
import com.cpu.quikdata.data.health.diseasesrow.DiseasesRow

class DiseasesRepository(private val mDatabase: AppDatabase, val formId: String) :
    BaseUpdateableRepository<DiseasesRow>() {

    private val mDiseases = mDatabase.diseasesRowDao().getByFormId(formId)

    val diseases: LiveData<List<DiseasesRow>>
        get() = mDiseases

    override suspend fun updateData(data: DiseasesRow) {
        mDatabase.diseasesRowDao().update(data)
    }
}