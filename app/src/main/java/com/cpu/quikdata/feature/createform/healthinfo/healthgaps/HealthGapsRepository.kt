package com.cpu.quikdata.feature.createform.healthinfo.healthgaps

import androidx.lifecycle.LiveData
import com.cpu.quikdata.data.AppDatabase
import com.cpu.quikdata.data.health.healthgaps.HealthGaps
import com.cpu.quikdata.utils.runOnIoThread
import javax.inject.Inject

class HealthGapsRepository @Inject constructor(private val mDatabase: AppDatabase, formId: String) {

    private val mHealthGaps = mDatabase.healthGapsDao().getByFormId(formId)

    val healthGaps : LiveData<HealthGaps>
        get() = mHealthGaps

    fun updateData(data: HealthGaps) {
        runOnIoThread {
            val oldGaps = mHealthGaps.value!!
            oldGaps.copyFrom(data)
            mDatabase.healthGapsDao().update(oldGaps)
        }
    }
}