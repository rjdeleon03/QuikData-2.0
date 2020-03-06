package com.cpu.quikdata.feature.createform.healthinfo.psychosocial

import androidx.lifecycle.LiveData
import com.cpu.quikdata.data.AppDatabase
import com.cpu.quikdata.data.health.psychosocialrow.PsychosocialRow
import com.cpu.quikdata.utils.runOnIoThread
import javax.inject.Inject

class PsychosocialRepository @Inject constructor(private val mDatabase: AppDatabase, formId: String) {

    private val mPsychosocial = mDatabase.psychosocialRowDao().getByFormId(formId)

    val psychosocial: LiveData<List<PsychosocialRow>>
        get() = mPsychosocial

    fun updateData(data: PsychosocialRow) {
        runOnIoThread {
            mDatabase.psychosocialRowDao().update(data)
        }
    }
}