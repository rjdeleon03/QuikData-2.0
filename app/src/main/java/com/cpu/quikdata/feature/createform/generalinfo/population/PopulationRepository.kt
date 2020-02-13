package com.cpu.quikdata.feature.createform.generalinfo.population

import androidx.lifecycle.LiveData
import com.cpu.quikdata.base.BaseUpdateableRepository
import com.cpu.quikdata.data.AppDatabase
import com.cpu.quikdata.data.generalinfo.populationrow.PopulationRow
import com.cpu.quikdata.utils.runOnIoThread

class PopulationRepository(private val mDatabase: AppDatabase, val formId: String) :
    BaseUpdateableRepository<PopulationRow>() {

    private val mPopulation = mDatabase.populationRowDao().getByFormId(formId)

    val population: LiveData<List<PopulationRow>>
        get() = mPopulation

    override fun updateData(data: PopulationRow) {
        runOnIoThread {
            mDatabase.populationRowDao().update(data)
        }
    }
}