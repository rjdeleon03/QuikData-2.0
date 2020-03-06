package com.cpu.quikdata.feature.createform.healthinfo.healthcoping

import androidx.lifecycle.LiveData
import com.cpu.quikdata.data.AppDatabase
import com.cpu.quikdata.data.health.healthcoping.HealthCoping
import com.cpu.quikdata.utils.runOnIoThread
import javax.inject.Inject

class HealthCopingRepository @Inject constructor(private val mDatabase: AppDatabase, formId: String) {

    private val mHealthCoping = mDatabase.healthCopingDao().getByFormId(formId)

    val healthCoping : LiveData<HealthCoping>
        get() = mHealthCoping

    fun updateData(data: HealthCoping) {
        runOnIoThread {
            val oldCoping = mHealthCoping.value!!
            oldCoping.copyFrom(data)
            mDatabase.healthCopingDao().update(oldCoping)
        }
    }
}