package com.cpu.quikdata.feature.createform.generalinfo.calamityinfo

import androidx.lifecycle.LiveData
import com.cpu.quikdata.data.generalinfo.calamityinfo.CalamityInfo
import com.cpu.quikdata.data.generalinfo.calamityinfo.CalamityInfoDao
import com.cpu.quikdata.di.component.repository.DaggerCalamityInfoRepositoryComponent
import com.cpu.quikdata.di.module.DatabaseModule
import com.cpu.quikdata.feature.QuikDataApp
import com.cpu.quikdata.utils.runOnIoThread
import javax.inject.Inject

class CalamityInfoRepository(application: QuikDataApp, formId: String) {

    @Inject
    lateinit var calamityInfoDao: CalamityInfoDao

    private val mCalamityInfo: LiveData<CalamityInfo>

    init {
        DaggerCalamityInfoRepositoryComponent
            .builder()
            .databaseModule(DatabaseModule(application))
            .build()
            .inject(this)

        mCalamityInfo = calamityInfoDao.getByFormId(formId)
    }

    val calamityInfo: LiveData<CalamityInfo>
        get() = mCalamityInfo

    fun updateData(data: CalamityInfo) {
        runOnIoThread {
            mCalamityInfo.value?.let {
                it.copyFrom(data)
                calamityInfoDao.update(it)
            }
        }
    }
}