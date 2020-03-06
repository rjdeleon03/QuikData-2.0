package com.cpu.quikdata.feature.createform.generalinfo.calamityinfo

import androidx.lifecycle.LiveData
import com.cpu.quikdata.data.AppDatabase
import com.cpu.quikdata.data.generalinfo.calamityinfo.CalamityInfo
import com.cpu.quikdata.utils.runOnIoThread
import javax.inject.Inject

class CalamityInfoRepository @Inject constructor(
    private val mDatabase: AppDatabase, formId: String) {

    private val mCalamityInfo = mDatabase.calamityInfoDao().getByFormId(formId)

    val calamityInfo: LiveData<CalamityInfo>
        get() = mCalamityInfo

    fun updateData(data: CalamityInfo) {
        runOnIoThread {
            val oldCalamityInfo = mCalamityInfo.value!!
            oldCalamityInfo.copyFrom(data)
            mDatabase.calamityInfoDao().update(oldCalamityInfo)
        }
    }
}