package com.cpu.quikdata.feature.createform.generalinfo.causeofdeath

import androidx.lifecycle.LiveData
import com.cpu.quikdata.data.generalinfo.causeofdeath.CauseOfDeathRow
import com.cpu.quikdata.data.generalinfo.causeofdeath.CauseOfDeathRowDao
import com.cpu.quikdata.di.component.repository.DaggerCauseOfDeathRepositoryComponent
import com.cpu.quikdata.di.module.DatabaseModule
import com.cpu.quikdata.feature.QuikDataApp
import com.cpu.quikdata.utils.runOnIoThread
import javax.inject.Inject

class CauseOfDeathRepository(application: QuikDataApp, formId: String) {

    @Inject
    lateinit var causeOfDeathRowDao: CauseOfDeathRowDao

    private val mCausesOfDeath: LiveData<List<CauseOfDeathRow>>

    init {

        DaggerCauseOfDeathRepositoryComponent
            .builder()
            .databaseModule(DatabaseModule(application))
            .build()
            .inject(this)

        mCausesOfDeath = causeOfDeathRowDao.getByFormId(formId)
    }

    val causesOfDeath: LiveData<List<CauseOfDeathRow>>
        get() = mCausesOfDeath

    fun updateData(data: CauseOfDeathRow) {
        runOnIoThread {
            causeOfDeathRowDao.update(data)
        }
    }
}