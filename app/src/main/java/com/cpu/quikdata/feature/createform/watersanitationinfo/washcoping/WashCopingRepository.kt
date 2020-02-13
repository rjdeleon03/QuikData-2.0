package com.cpu.quikdata.feature.createform.watersanitationinfo.washcoping

import androidx.lifecycle.LiveData
import com.cpu.quikdata.base.BaseUpdateableRepository
import com.cpu.quikdata.data.AppDatabase
import com.cpu.quikdata.data.watersanitationinfo.washcoping.WashCoping
import com.cpu.quikdata.utils.runOnIoThread

class WashCopingRepository(private val mDatabase: AppDatabase, val formId: String) :
    BaseUpdateableRepository<WashCoping>() {

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