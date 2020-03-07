package com.cpu.quikdata.feature.createform.watersanitationinfo.washgaps

import androidx.lifecycle.LiveData
import com.cpu.quikdata.data.AppDatabase
import com.cpu.quikdata.data.watersanitationinfo.washgaps.WashGaps
import javax.inject.Inject

class WashGapsRepository @Inject constructor(private val mDatabase: AppDatabase, formId: String) {

    private val mWashGaps = mDatabase.washGapsDao().getByFormId(formId)

    val washGaps: LiveData<WashGaps>
        get() = mWashGaps

    suspend fun updateData(data: WashGaps) {
        mWashGaps.value?.apply {
            copyFrom(data)
            mDatabase.washGapsDao().update(this)
        }
    }
}