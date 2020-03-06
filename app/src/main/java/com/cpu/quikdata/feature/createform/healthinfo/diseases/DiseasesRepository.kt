package com.cpu.quikdata.feature.createform.healthinfo.diseases

import androidx.lifecycle.LiveData
import com.cpu.quikdata.data.AppDatabase
import com.cpu.quikdata.data.health.diseasesrow.DiseasesRow
import com.cpu.quikdata.utils.runOnIoThread
import javax.inject.Inject

class DiseasesRepository @Inject constructor(private val mDatabase: AppDatabase, formId: String) {

    private val mDiseases = mDatabase.diseasesRowDao().getByFormId(formId)

    val diseases: LiveData<List<DiseasesRow>>
        get() = mDiseases

    fun updateData(data: DiseasesRow) {
        runOnIoThread {
            mDatabase.diseasesRowDao().update(data)
        }
    }
}