package com.cpu.quikdata.feature.createform.generalinfo.vulnerable

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.cpu.quikdata.common.runOnIoThread
import com.cpu.quikdata.data.generalinfo.vulnerablerow.VulnerableRow
import javax.inject.Inject

class VulnerableViewModel @Inject constructor (private val mRepository: VulnerableRepository)
    : ViewModel() {

    val vulnerable: LiveData<List<VulnerableRow>>
        get() = mRepository.vulnerable

    fun updateRow(vulnerableRow: VulnerableRow) =
        runOnIoThread { mRepository.updateData(vulnerableRow) }
}
