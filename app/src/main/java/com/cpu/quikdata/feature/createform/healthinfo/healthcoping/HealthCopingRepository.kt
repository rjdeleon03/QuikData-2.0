package com.cpu.quikdata.feature.createform.healthinfo.healthcoping

import androidx.lifecycle.LiveData
import com.cpu.quikdata.data.AppDatabase
import com.cpu.quikdata.data.health.healthcoping.HealthCoping
import javax.inject.Inject

class HealthCopingRepository @Inject constructor(
    private val mDatabase: AppDatabase,
    formId: String
) {

    private val mHealthCoping = mDatabase.healthCopingDao().getByFormId(formId)

    val healthCoping: LiveData<HealthCoping>
        get() = mHealthCoping

    suspend fun updateData(data: HealthCoping) {
        mHealthCoping.value?.apply {
            copyFrom(data)
            mDatabase.healthCopingDao().update(this)
        }
    }
}