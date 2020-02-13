package com.cpu.quikdata.feature.createform.generalinfo.calamityinfo

import androidx.lifecycle.LiveData
import com.cpu.quikdata.base.BaseUpdateableRepository
import com.cpu.quikdata.data.AppDatabase
import com.cpu.quikdata.data.generalinfo.calamityinfo.CalamityInfo
import com.cpu.quikdata.utils.runOnIoThread

class CalamityInfoRepository(private val mDatabase: AppDatabase, val formId: String) :
    BaseUpdateableRepository<CalamityInfo>() {

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