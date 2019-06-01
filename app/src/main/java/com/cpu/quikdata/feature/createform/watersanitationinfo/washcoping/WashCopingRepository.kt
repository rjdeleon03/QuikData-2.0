package com.cpu.quikdata.feature.createform.watersanitationinfo.washcoping

import android.app.Application
import androidx.lifecycle.LiveData
import com.cpu.quikdata.base.BaseRepository
import com.cpu.quikdata.data.watersanitationinfo.washcoping.WashCoping
import com.cpu.quikdata.utils.runOnIoThread

class WashCopingRepository(application: Application, formId: String) :
    BaseRepository<WashCoping>(application) {

    private val mWashCoping = mDatabase.washCopingDao().getByFormId(formId)

    val washCoping : LiveData<WashCoping>
        get() = mWashCoping

    override fun updateData(data: WashCoping) {
        runOnIoThread {
            val oldCoping = mWashCoping.value!!
            oldCoping.copyFrom(data)
            mDatabase.washCopingDao().update(oldCoping)
        }
    }
}