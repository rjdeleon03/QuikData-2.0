package com.cpu.quikdata.feature.createform.healthinfo.healthgaps

import androidx.lifecycle.LiveData
import com.cpu.quikdata.base.BaseUpdateableRepository
import com.cpu.quikdata.data.AppDatabase
import com.cpu.quikdata.data.health.healthgaps.HealthGaps
import com.cpu.quikdata.utils.runOnIoThread

class HealthGapsRepository(private val mDatabase: AppDatabase, val formId: String) :
    BaseUpdateableRepository<HealthGaps>() {

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