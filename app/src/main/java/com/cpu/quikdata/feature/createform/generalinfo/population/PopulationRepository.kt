package com.cpu.quikdata.feature.createform.generalinfo.population

import androidx.lifecycle.LiveData
import com.cpu.quikdata.data.AppDatabase
import com.cpu.quikdata.data.generalinfo.populationrow.PopulationRow
import javax.inject.Inject

class PopulationRepository @Inject constructor(
    private val mDatabase: AppDatabase, formId: String
) {

    private val mPopulation = mDatabase.populationRowDao().getByFormId(formId)

    val population: LiveData<List<PopulationRow>>
        get() = mPopulation

    suspend fun updateData(data: PopulationRow) {
        mDatabase.populationRowDao().update(data)
    }
}