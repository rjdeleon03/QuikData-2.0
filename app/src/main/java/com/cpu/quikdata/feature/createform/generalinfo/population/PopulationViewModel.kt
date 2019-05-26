package com.cpu.quikdata.feature.createform.generalinfo.population

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.cpu.quikdata.data.generalinfo.population.PopulationComplete

class PopulationViewModel(application: Application, formId: String) : AndroidViewModel(application) {

    private val mRepository = PopulationRepository(application, formId)

    val population: LiveData<PopulationComplete>
        get() = mRepository.population
}
