package com.cpu.quikdata.feature.createform.healthinfo.psychosocial

import androidx.lifecycle.LiveData
import com.cpu.quikdata.data.AppDatabase
import com.cpu.quikdata.data.health.psychosocialrow.PsychosocialRow
import javax.inject.Inject

class PsychosocialRepository @Inject constructor(
    private val mDatabase: AppDatabase,
    formId: String
) {

    private val mPsychosocial = mDatabase.psychosocialRowDao().getByFormId(formId)

    val psychosocial: LiveData<List<PsychosocialRow>>
        get() = mPsychosocial

    suspend fun updateData(data: PsychosocialRow) {
        mDatabase.psychosocialRowDao().update(data)
    }
}