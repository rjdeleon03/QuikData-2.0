package com.cpu.quikdata.feature.createform.healthinfo.psychosocial

import android.app.Application
import androidx.lifecycle.LiveData
import com.cpu.quikdata.base.BaseRepository
import com.cpu.quikdata.data.health.psychosocialrow.PsychosocialRow
import com.cpu.quikdata.utils.runOnIoThread

class PsychosocialRepository(application: Application, formId: String) :
    BaseRepository<PsychosocialRow>(application) {

    private val mPsychosocial = mDatabase.psychosocialRowDao().getByFormId(formId)

    val psychosocial: LiveData<List<PsychosocialRow>>
        get() = mPsychosocial

    override fun updateData(data: PsychosocialRow) {
        runOnIoThread {
            mDatabase.psychosocialRowDao().update(data)
        }
    }
}