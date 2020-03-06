package com.cpu.quikdata.feature.createform.generalinfo.population

import androidx.lifecycle.LiveData
import com.cpu.quikdata.data.AppDatabase
import com.cpu.quikdata.data.generalinfo.populationrow.PopulationRow
import com.cpu.quikdata.utils.runOnIoThread
import javax.inject.Inject

class PopulationRepository @Inject constructor(
    private val mDatabase: AppDatabase, private val mFormId: String) {

    private val mPopulation = mDatabase.populationRowDao().getByFormId(mFormId)

    val population: LiveData<List<PopulationRow>>
        get() = mPopulation

    fun updateData(data: PopulationRow) {
        runOnIoThread {
            mDatabase.populationRowDao().update(data)
        }
    }
}