package com.cpu.quikdata.feature.createform.healthinfo.healthgaps

import androidx.lifecycle.LiveData
import com.cpu.quikdata.data.AppDatabase
import com.cpu.quikdata.data.health.healthgaps.HealthGaps
import javax.inject.Inject

class HealthGapsRepository @Inject constructor(private val mDatabase: AppDatabase, formId: String) {

    private val mHealthGaps = mDatabase.healthGapsDao().getByFormId(formId)

    val healthGaps: LiveData<HealthGaps>
        get() = mHealthGaps

    suspend fun updateData(data: HealthGaps) {
        mHealthGaps.value?.apply {
            copyFrom(data)
            mDatabase.healthGapsDao().update(this)
        }
    }
}