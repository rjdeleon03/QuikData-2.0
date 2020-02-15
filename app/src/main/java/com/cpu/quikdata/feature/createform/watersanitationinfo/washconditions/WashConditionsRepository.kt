package com.cpu.quikdata.feature.createform.watersanitationinfo.washconditions

import androidx.lifecycle.LiveData
import com.cpu.quikdata.base.BaseUpdateableRepository
import com.cpu.quikdata.data.AppDatabase
import com.cpu.quikdata.data.watersanitationinfo.washconditions.WashConditions

class WashConditionsRepository(private val mDatabase: AppDatabase, val formId: String) :
    BaseUpdateableRepository<WashConditions>() {

    private val mWashConditions = mDatabase.washConditionsDao().getByFormId(formId)

    val washConditions : LiveData<WashConditions>
        get() = mWashConditions

    override suspend fun updateData(data: WashConditions) {
        mWashConditions.value?.let {
            it.copyFrom(data)
            mDatabase.washConditionsDao().update(it)
        }
    }
}