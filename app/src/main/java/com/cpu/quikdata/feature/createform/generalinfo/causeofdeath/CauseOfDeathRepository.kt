package com.cpu.quikdata.feature.createform.generalinfo.causeofdeath

import androidx.lifecycle.LiveData
import com.cpu.quikdata.data.AppDatabase
import com.cpu.quikdata.data.generalinfo.causeofdeath.CauseOfDeathRow
import javax.inject.Inject

class CauseOfDeathRepository @Inject constructor(
    private val mDatabase: AppDatabase, formId: String
) {

    private val mCausesOfDeath = mDatabase.causeOfDeathRowDao().getByFormId(formId)

    val causesOfDeath: LiveData<List<CauseOfDeathRow>>
        get() = mCausesOfDeath

    suspend fun updateData(data: CauseOfDeathRow) {
        mDatabase.causeOfDeathRowDao().update(data)
    }
}