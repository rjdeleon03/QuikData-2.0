package com.cpu.quikdata.feature.createform.healthinfo.psychosocial

import androidx.lifecycle.LiveData
import com.cpu.quikdata.base.BaseUpdateableRepository
import com.cpu.quikdata.data.AppDatabase
import com.cpu.quikdata.data.health.psychosocialrow.PsychosocialRow

class PsychosocialRepository(private val mDatabase: AppDatabase, val formId: String) :
    BaseUpdateableRepository<PsychosocialRow>() {

    private val mPsychosocial = mDatabase.psychosocialRowDao().getByFormId(formId)

    val psychosocial: LiveData<List<PsychosocialRow>>
        get() = mPsychosocial

    override suspend fun updateData(data: PsychosocialRow) {
        mDatabase.psychosocialRowDao().update(data)
    }
}