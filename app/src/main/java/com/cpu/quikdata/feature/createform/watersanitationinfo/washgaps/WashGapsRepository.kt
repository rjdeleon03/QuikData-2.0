package com.cpu.quikdata.feature.createform.watersanitationinfo.washgaps

import androidx.lifecycle.LiveData
import com.cpu.quikdata.data.AppDatabase
import com.cpu.quikdata.data.watersanitationinfo.washgaps.WashGaps
import com.cpu.quikdata.utils.runOnIoThread
import javax.inject.Inject

class WashGapsRepository @Inject constructor(private val mDatabase: AppDatabase, formId: String) {

    private val mWashGaps = mDatabase.washGapsDao().getByFormId(formId)

    val washGaps : LiveData<WashGaps>
        get() = mWashGaps

    fun updateData(data: WashGaps) {
        runOnIoThread {
            val oldGaps = mWashGaps.value!!
            oldGaps.copyFrom(data)
            mDatabase.washGapsDao().update(oldGaps)
        }
    }
}