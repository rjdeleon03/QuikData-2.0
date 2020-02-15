package com.cpu.quikdata.feature.createform.generalinfo.causeofdeath

import androidx.lifecycle.LiveData
import com.cpu.quikdata.base.BaseUpdateableRepository
import com.cpu.quikdata.data.AppDatabase
import com.cpu.quikdata.data.generalinfo.causeofdeath.CauseOfDeathRow

class CauseOfDeathRepository(private val mDatabase: AppDatabase, val formId: String) :
    BaseUpdateableRepository<CauseOfDeathRow>() {

    private val mCausesOfDeath = mDatabase.causeOfDeathRowDao().getByFormId(formId)

    val causesOfDeath: LiveData<List<CauseOfDeathRow>>
        get() = mCausesOfDeath

    override suspend fun updateData(data: CauseOfDeathRow) {
        mDatabase.causeOfDeathRowDao().update(data)
    }
}