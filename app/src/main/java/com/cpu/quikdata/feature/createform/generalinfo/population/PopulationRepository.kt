package com.cpu.quikdata.feature.createform.generalinfo.population

import androidx.lifecycle.LiveData
import com.cpu.quikdata.base.BaseUpdateableRepository
import com.cpu.quikdata.data.AppDatabase
import com.cpu.quikdata.data.generalinfo.populationrow.PopulationRow

class PopulationRepository(private val mDatabase: AppDatabase, val formId: String) :
    BaseUpdateableRepository<PopulationRow>() {

    private val mPopulation = mDatabase.populationRowDao().getByFormId(formId)

    val population: LiveData<List<PopulationRow>>
        get() = mPopulation

    override suspend fun updateData(data: PopulationRow) {
        mDatabase.populationRowDao().update(data)
    }
}