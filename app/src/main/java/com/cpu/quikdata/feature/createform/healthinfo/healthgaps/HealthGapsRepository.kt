package com.cpu.quikdata.feature.createform.healthinfo.healthgaps

import androidx.lifecycle.LiveData
import com.cpu.quikdata.base.BaseUpdateableRepository
import com.cpu.quikdata.data.AppDatabase
import com.cpu.quikdata.data.health.healthgaps.HealthGaps

class HealthGapsRepository(private val mDatabase: AppDatabase, val formId: String) :
    BaseUpdateableRepository<HealthGaps>() {

    private val mHealthGaps = mDatabase.healthGapsDao().getByFormId(formId)

    val healthGaps : LiveData<HealthGaps>
        get() = mHealthGaps

    override suspend fun updateData(data: HealthGaps) {
        mHealthGaps.value?.let {
            it.copyFrom(data)
            mDatabase.healthGapsDao().update(it)
        }
    }
}