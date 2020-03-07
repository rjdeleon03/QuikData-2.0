package com.cpu.quikdata.feature.createform.healthinfo.specialneeds

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.cpu.quikdata.common.runOnIoThread
import com.cpu.quikdata.data.health.specialneedsrow.SpecialNeedsRow
import javax.inject.Inject

class SpecialNeedsViewModel @Inject constructor(private val mRepository: SpecialNeedsRepository) :
    ViewModel() {

    val specialNeeds: LiveData<List<SpecialNeedsRow>>
        get() = mRepository.specialNeeds

    fun updateRow(specialNeedsRow: SpecialNeedsRow) =
        runOnIoThread {
            mRepository.updateData(specialNeedsRow)
        }
}
