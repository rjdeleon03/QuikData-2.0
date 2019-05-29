package com.cpu.quikdata.feature.createform.generalinfo.population

import android.app.Application
import androidx.lifecycle.LiveData
import com.cpu.quikdata.base.BaseRepository
import com.cpu.quikdata.data.generalinfo.populationrow.PopulationRow
import com.cpu.quikdata.utils.runOnIoThread

class PopulationRepository(application: Application, formId: String) :
    BaseRepository<PopulationRow>(application) {

    private val mPopulation = mDatabase.populationRowDao().getByFormId(formId)

    val population: LiveData<List<PopulationRow>>
        get() = mPopulation

    override fun updateData(data: PopulationRow) {
        runOnIoThread {
            mDatabase.populationRowDao().update(data)
        }
    }
}