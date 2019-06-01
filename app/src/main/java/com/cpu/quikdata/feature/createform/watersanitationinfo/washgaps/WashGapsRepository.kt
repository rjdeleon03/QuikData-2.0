package com.cpu.quikdata.feature.createform.watersanitationinfo.washgaps

import android.app.Application
import androidx.lifecycle.LiveData
import com.cpu.quikdata.base.BaseRepository
import com.cpu.quikdata.data.watersanitationinfo.washgaps.WashGaps
import com.cpu.quikdata.utils.runOnIoThread

class WashGapsRepository(application: Application, formId: String) :
    BaseRepository<WashGaps>(application) {

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