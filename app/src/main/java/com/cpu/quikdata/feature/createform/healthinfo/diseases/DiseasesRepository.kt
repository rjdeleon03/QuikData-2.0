package com.cpu.quikdata.feature.createform.healthinfo.diseases

import androidx.lifecycle.LiveData
import com.cpu.quikdata.base.BaseUpdateableRepository
import com.cpu.quikdata.data.AppDatabase
import com.cpu.quikdata.data.health.diseasesrow.DiseasesRow
import com.cpu.quikdata.utils.runOnIoThread

class DiseasesRepository(private val mDatabase: AppDatabase, val formId: String) :
    BaseUpdateableRepository<DiseasesRow>() {

    private val mDiseases = mDatabase.diseasesRowDao().getByFormId(formId)

    val diseases: LiveData<List<DiseasesRow>>
        get() = mDiseases

    override fun updateData(data: DiseasesRow) {
        runOnIoThread {
            mDatabase.diseasesRowDao().update(data)
        }
    }
}