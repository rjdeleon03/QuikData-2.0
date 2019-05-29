package com.cpu.quikdata.feature.createform.generalinfo.population

import android.app.Application
import androidx.lifecycle.LiveData
import com.cpu.quikdata.data.AppDatabase
import com.cpu.quikdata.data.generalinfo.populationrow.PopulationRow
import com.cpu.quikdata.utils.runOnIoThread

class PopulationRepository(application: Application, formId: String) {

    private val mDatabase = AppDatabase.get(application)
    private val mPopulation = mDatabase.populationRowDao().getByFormId(formId)

    val population: LiveData<List<PopulationRow>>
        get() = mPopulation

    fun updateRow(populationRow: PopulationRow) {
        runOnIoThread {
            mDatabase.populationRowDao().insert(populationRow)
        }
    }
}