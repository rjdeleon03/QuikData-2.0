package com.cpu.quikdata.feature.createform.watersanitationinfo.washcoping

import androidx.lifecycle.LiveData
import com.cpu.quikdata.data.AppDatabase
import com.cpu.quikdata.data.watersanitationinfo.washcoping.WashCoping
import com.cpu.quikdata.utils.runOnIoThread
import javax.inject.Inject

class WashCopingRepository @Inject constructor(private val mDatabase: AppDatabase, formId: String) {

    private val mWashCoping = mDatabase.washCopingDao().getByFormId(formId)

    val washCoping : LiveData<WashCoping>
        get() = mWashCoping

    fun updateData(data: WashCoping) {
        runOnIoThread {
            val oldCoping = mWashCoping.value!!
            oldCoping.copyFrom(data)
            mDatabase.washCopingDao().update(oldCoping)
        }
    }
}