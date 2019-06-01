package com.cpu.quikdata.feature.createform.healthinfo.healthgaps

import android.app.Application
import androidx.lifecycle.LiveData
import com.cpu.quikdata.base.BaseRepository
import com.cpu.quikdata.data.health.healthgaps.HealthGaps
import com.cpu.quikdata.utils.runOnIoThread

class HealthGapsRepository(application: Application, formId: String) :
    BaseRepository<HealthGaps>(application) {

    private val mHealthGaps = mDatabase.healthGapsDao().getByFormId(formId)

    val healthGaps : LiveData<HealthGaps>
        get() = mHealthGaps

    override fun updateData(data: HealthGaps) {
        runOnIoThread {
            val oldGaps = mHealthGaps.value!!
            oldGaps.copyFrom(data)
            mDatabase.healthGapsDao().update(oldGaps)
        }
    }
}