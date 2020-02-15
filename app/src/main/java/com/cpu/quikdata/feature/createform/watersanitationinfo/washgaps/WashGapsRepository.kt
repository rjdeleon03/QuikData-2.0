package com.cpu.quikdata.feature.createform.watersanitationinfo.washgaps

import androidx.lifecycle.LiveData
import com.cpu.quikdata.base.BaseUpdateableRepository
import com.cpu.quikdata.data.AppDatabase
import com.cpu.quikdata.data.watersanitationinfo.washgaps.WashGaps
import com.cpu.quikdata.utils.runOnIoThread

class WashGapsRepository(private val mDatabase: AppDatabase, val formId: String) :
    BaseUpdateableRepository<WashGaps>() {

    private val mWashGaps = mDatabase.washGapsDao().getByFormId(formId)

    val washGaps : LiveData<WashGaps>
        get() = mWashGaps

    override fun updateData(data: WashGaps) {
        runOnIoThread {
            val oldGaps = mWashGaps.value!!
            oldGaps.copyFrom(data)
            mDatabase.washGapsDao().update(oldGaps)
        }
    }
}