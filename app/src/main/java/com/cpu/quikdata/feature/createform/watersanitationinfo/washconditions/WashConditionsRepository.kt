package com.cpu.quikdata.feature.createform.watersanitationinfo.washconditions

import androidx.lifecycle.LiveData
import com.cpu.quikdata.data.AppDatabase
import com.cpu.quikdata.data.watersanitationinfo.washconditions.WashConditions
import com.cpu.quikdata.utils.runOnIoThread
import javax.inject.Inject

class WashConditionsRepository @Inject constructor(private val mDatabase: AppDatabase, formId: String) {

    private val mWashConditions = mDatabase.washConditionsDao().getByFormId(formId)

    val washConditions : LiveData<WashConditions>
        get() = mWashConditions

    fun updateData(data: WashConditions) {
        runOnIoThread {
            val oldConditions = mWashConditions.value!!
            oldConditions.copyFrom(data)
            mDatabase.washConditionsDao().update(oldConditions)
        }
    }
}