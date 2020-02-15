package com.cpu.quikdata.feature.createform.watersanitationinfo.washgaps

import androidx.lifecycle.LiveData
import com.cpu.quikdata.base.BaseUpdateableRepository
import com.cpu.quikdata.data.AppDatabase
import com.cpu.quikdata.data.watersanitationinfo.washgaps.WashGaps

class WashGapsRepository(private val mDatabase: AppDatabase, val formId: String) :
    BaseUpdateableRepository<WashGaps>() {

    private val mWashGaps = mDatabase.washGapsDao().getByFormId(formId)

    val washGaps : LiveData<WashGaps>
        get() = mWashGaps

    override suspend fun updateData(data: WashGaps) {
        mWashGaps.value?.let {
            it.copyFrom(data)
            mDatabase.washGapsDao().update(it)
        }
    }
}