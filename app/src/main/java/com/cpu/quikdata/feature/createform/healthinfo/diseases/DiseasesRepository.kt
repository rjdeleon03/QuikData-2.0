package com.cpu.quikdata.feature.createform.healthinfo.diseases

import android.app.Application
import androidx.lifecycle.LiveData
import com.cpu.quikdata.base.BaseRepository
import com.cpu.quikdata.data.health.diseasesrow.DiseasesRow
import com.cpu.quikdata.utils.runOnIoThread

class DiseasesRepository(application: Application, formId: String) :
    BaseRepository<DiseasesRow>(application) {

    private val mDiseases = mDatabase.diseasesRowDao().getByFormId(formId)

    val diseases: LiveData<List<DiseasesRow>>
        get() = mDiseases

    override fun updateData(data: DiseasesRow) {
        runOnIoThread {
            mDatabase.diseasesRowDao().update(data)
        }
    }
}