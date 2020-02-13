package com.cpu.quikdata.feature.createform.healthinfo.healthcoping

import androidx.lifecycle.LiveData
import com.cpu.quikdata.base.BaseUpdateableRepository
import com.cpu.quikdata.data.AppDatabase
import com.cpu.quikdata.data.health.healthcoping.HealthCoping
import com.cpu.quikdata.utils.runOnIoThread

class HealthCopingRepository(private val mDatabase: AppDatabase, val formId: String) :
    BaseUpdateableRepository<HealthCoping>() {

    private val mHealthCoping = mDatabase.healthCopingDao().getByFormId(formId)

    val healthCoping : LiveData<HealthCoping>
        get() = mHealthCoping

    override fun updateData(data: HealthCoping) {
        runOnIoThread {
            val oldCoping = mHealthCoping.value!!
            oldCoping.copyFrom(data)
            mDatabase.healthCopingDao().update(oldCoping)
        }
    }
}