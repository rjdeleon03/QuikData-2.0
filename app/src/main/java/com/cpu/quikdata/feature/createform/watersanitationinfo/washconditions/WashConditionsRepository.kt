package com.cpu.quikdata.feature.createform.watersanitationinfo.washconditions

import android.app.Application
import androidx.lifecycle.LiveData
import com.cpu.quikdata.base.BaseRepository
import com.cpu.quikdata.data.watersanitationinfo.washconditions.WashConditions
import com.cpu.quikdata.utils.runOnIoThread

class WashConditionsRepository(application: Application, formId: String) :
    BaseRepository<WashConditions>(application) {

    private val mWashConditions = mDatabase.washConditionsDao().getByFormId(formId)

    val washConditions : LiveData<WashConditions>
        get() = mWashConditions

    override fun updateData(data: WashConditions) {
        runOnIoThread {
            val oldConditions = mWashConditions.value!!
            oldConditions.copyFrom(data)
            mDatabase.washConditionsDao().update(oldConditions)
        }
    }
}