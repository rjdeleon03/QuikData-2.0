package com.cpu.quikdata.feature.createform.generalinfo.population

import android.app.Application
import androidx.lifecycle.LiveData
import com.cpu.quikdata.data.AppDatabase
import com.cpu.quikdata.data.generalinfo.population.PopulationComplete

class PopulationRepository(application: Application, formId: String) {

    private val mDatabase = AppDatabase.get(application)
    private val mPopulation = mDatabase.populationDao().getByFormId(formId)

    val population: LiveData<PopulationComplete>
        get() = mPopulation
}