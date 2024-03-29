package com.cpu.quikdata.feature.createform.generalinfo.population

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.cpu.quikdata.data.generalinfo.populationrow.PopulationRow

class PopulationViewModel(application: Application, formId: String) : AndroidViewModel(application) {

    private val mRepository = PopulationRepository(application, formId)

    val population: LiveData<List<PopulationRow>>
        get() = mRepository.population

    fun updateRow(populationRow: PopulationRow) = mRepository.updateData(populationRow)
}
