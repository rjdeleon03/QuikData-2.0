package com.cpu.quikdata.feature.createform.healthinfo.diseases

import androidx.lifecycle.LiveData
import com.cpu.quikdata.data.AppDatabase
import com.cpu.quikdata.data.health.diseasesrow.DiseasesRow
import javax.inject.Inject

class DiseasesRepository @Inject constructor(private val mDatabase: AppDatabase, formId: String) {

    private val mDiseases = mDatabase.diseasesRowDao().getByFormId(formId)

    val diseases: LiveData<List<DiseasesRow>>
        get() = mDiseases

    suspend fun updateData(data: DiseasesRow) {
        mDatabase.diseasesRowDao().update(data)
    }
}