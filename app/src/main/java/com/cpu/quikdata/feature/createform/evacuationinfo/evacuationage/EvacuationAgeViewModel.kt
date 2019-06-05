package com.cpu.quikdata.feature.createform.evacuationinfo.evacuationage

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.cpu.quikdata.data.evacuation.evacuationagerow.EvacuationAgeRow

class EvacuationAgeViewModel(application: Application, formId: String) : AndroidViewModel(application) {

    private val mRepository = EvacuationAgeRepository(application, formId)

    val evacuationAge: LiveData<List<EvacuationAgeRow>>
        get() = mRepository.evacuationAge

    fun updateRow(evacuationAgeRow: EvacuationAgeRow) = mRepository.updateData(evacuationAgeRow)
}
