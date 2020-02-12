package com.cpu.quikdata.feature.createform.generalinfo.population

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.cpu.quikdata.data.generalinfo.populationrow.PopulationRow
import javax.inject.Inject

class PopulationViewModel @Inject constructor(private val mRepository: PopulationRepository)
    : ViewModel() {

    val population: LiveData<List<PopulationRow>>
        get() = mRepository.population

    fun updateRow(populationRow: PopulationRow) = mRepository.updateData(populationRow)
}
