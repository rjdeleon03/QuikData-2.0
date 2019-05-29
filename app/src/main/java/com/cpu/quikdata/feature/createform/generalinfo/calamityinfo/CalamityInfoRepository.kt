package com.cpu.quikdata.feature.createform.generalinfo.calamityinfo

import android.app.Application
import androidx.lifecycle.LiveData
import com.cpu.quikdata.base.BaseRepository
import com.cpu.quikdata.data.AppDatabase
import com.cpu.quikdata.data.generalinfo.calamityinfo.CalamityInfo
import com.cpu.quikdata.utils.runOnIoThread

class CalamityInfoRepository(application: Application, formId: String) :
    BaseRepository<CalamityInfo>(application) {

    private val mCalamityInfo = mDatabase.calamityInfoDao().getByFormId(formId)

    val calamityInfo: LiveData<CalamityInfo>
        get() = mCalamityInfo

    override fun updateData(data: CalamityInfo) {
        runOnIoThread {
            val oldCalamityInfo = mCalamityInfo.value!!
            oldCalamityInfo.copyFrom(data)
            mDatabase.calamityInfoDao().update(oldCalamityInfo)
        }
    }
}