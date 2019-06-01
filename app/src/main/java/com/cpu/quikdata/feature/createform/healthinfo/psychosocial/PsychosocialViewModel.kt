package com.cpu.quikdata.feature.createform.healthinfo.psychosocial

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.cpu.quikdata.data.health.psychosocialrow.PsychosocialRow

class PsychosocialViewModel(application: Application, formId: String) :
    AndroidViewModel(application) {

    private val mRepository = PsychosocialRepository(application, formId)

    val psychosocial: LiveData<List<PsychosocialRow>>
        get() = mRepository.psychosocial

    fun updateRow(psychosocialRow: PsychosocialRow) = mRepository.updateData(psychosocialRow)
}
