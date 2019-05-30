package com.cpu.quikdata.feature.createform.generalinfo.causeofdeath

import android.app.Application
import androidx.lifecycle.LiveData
import com.cpu.quikdata.base.BaseRepository
import com.cpu.quikdata.data.generalinfo.causeofdeath.CauseOfDeathRow
import com.cpu.quikdata.utils.runOnIoThread

class CauseOfDeathRepository(application: Application, formId: String) :
    BaseRepository<CauseOfDeathRow>(application) {

    private val mCausesOfDeath = mDatabase.causeOfDeathRowDao().getByFormId(formId)

    val causesOfDeath: LiveData<List<CauseOfDeathRow>>
        get() = mCausesOfDeath

    override fun updateData(data: CauseOfDeathRow) {
        runOnIoThread {
            mDatabase.causeOfDeathRowDao().update(data)
        }
    }
}