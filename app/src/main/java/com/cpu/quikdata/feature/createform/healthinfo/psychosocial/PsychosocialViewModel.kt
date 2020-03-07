package com.cpu.quikdata.feature.createform.healthinfo.psychosocial

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.cpu.quikdata.common.runOnIoThread
import com.cpu.quikdata.data.health.psychosocialrow.PsychosocialRow
import javax.inject.Inject

class PsychosocialViewModel @Inject constructor(private val mRepository: PsychosocialRepository) :
    ViewModel() {

    val psychosocial: LiveData<List<PsychosocialRow>>
        get() = mRepository.psychosocial

    fun updateRow(psychosocialRow: PsychosocialRow) =
        runOnIoThread { mRepository.updateData(psychosocialRow) }
}
