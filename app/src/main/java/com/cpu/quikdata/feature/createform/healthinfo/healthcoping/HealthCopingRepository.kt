package com.cpu.quikdata.feature.createform.healthinfo.healthcoping

import android.app.Application
import androidx.lifecycle.LiveData
import com.cpu.quikdata.base.BaseRepository
import com.cpu.quikdata.data.health.healthcoping.HealthCoping
import com.cpu.quikdata.utils.runOnIoThread

class HealthCopingRepository(application: Application, formId: String) :
    BaseRepository<HealthCoping>(application) {

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