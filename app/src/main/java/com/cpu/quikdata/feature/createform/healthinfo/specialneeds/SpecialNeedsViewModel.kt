package com.cpu.quikdata.feature.createform.healthinfo.specialneeds

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.cpu.quikdata.data.health.specialneedsrow.SpecialNeedsRow

class SpecialNeedsViewModel(application: Application, formId: String) :
    AndroidViewModel(application) {

    private val mRepository = SpecialNeedsRepository(application, formId)

    val specialNeeds: LiveData<List<SpecialNeedsRow>>
        get() = mRepository.specialNeeds

    fun updateRow(specialNeedsRow: SpecialNeedsRow) = mRepository.updateData(specialNeedsRow)
}
