package com.cpu.quikdata.feature.createform.generalinfo.population

import android.app.Application
import androidx.lifecycle.LiveData
import com.cpu.quikdata.data.AppDatabase
import com.cpu.quikdata.data.generalinfo.population.PopulationComplete
import com.cpu.quikdata.data.generalinfo.population.row.PopulationRow
import com.cpu.quikdata.utils.runOnIoThread
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class PopulationRepository(application: Application, formId: String) {

    private val mDatabase = AppDatabase.get(application)
    private val mPopulation = mDatabase.populationDao().getByFormId(formId)

    val population: LiveData<PopulationComplete>
        get() = mPopulation

    fun updateRow(populationRow: PopulationRow) {
        runOnIoThread {
            mDatabase.populationRowDao().insert(populationRow)
        }
    }
}