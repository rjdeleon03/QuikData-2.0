package com.cpu.quikdata.feature.createform.generalinfo.calamityinfo

import android.app.Application
import androidx.lifecycle.LiveData
import com.cpu.quikdata.data.AppDatabase
import com.cpu.quikdata.data.generalinfo.calamityinfo.CalamityInfo
import com.cpu.quikdata.utils.runOnIoThread

class CalamityInfoRepository(application: Application, formId: String) {

    private val mDatabase = AppDatabase.get(application)
    private val mCalamityInfo = mDatabase.calamityInfoDao().getByFormId(formId)

    val calamityInfo: LiveData<CalamityInfo>
        get() = mCalamityInfo

    fun updateCalamityInfo(calamityInfo: CalamityInfo) {
        runOnIoThread {
            val oldCalamityInfo = mCalamityInfo.value!!
            oldCalamityInfo.copyFrom(calamityInfo)
            mDatabase.calamityInfoDao().update(oldCalamityInfo)
        }
    }
}