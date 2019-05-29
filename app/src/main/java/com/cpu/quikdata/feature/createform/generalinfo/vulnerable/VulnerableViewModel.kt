package com.cpu.quikdata.feature.createform.generalinfo.vulnerable

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.cpu.quikdata.data.generalinfo.vulnerablerow.VulnerableRow

class VulnerableViewModel(application: Application, formId: String) : AndroidViewModel(application) {

    private val mRepository = VulnerableRepository(application, formId)

    val vulnerable: LiveData<List<VulnerableRow>>
        get() = mRepository.vulnerable

    fun updateRow(vulnerableRow: VulnerableRow) = mRepository.updateRow(vulnerableRow)
}
