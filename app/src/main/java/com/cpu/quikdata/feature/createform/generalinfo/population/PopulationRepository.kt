package com.cpu.quikdata.feature.createform.generalinfo.population

import androidx.lifecycle.LiveData
import com.cpu.quikdata.base.BaseRepository
import com.cpu.quikdata.data.generalinfo.populationrow.PopulationRow
import com.cpu.quikdata.data.generalinfo.populationrow.PopulationRowDao
import com.cpu.quikdata.di.component.repository.DaggerPopulationRepositoryComponent
import com.cpu.quikdata.di.module.DatabaseModule
import com.cpu.quikdata.feature.QuikDataApp
import com.cpu.quikdata.utils.runOnIoThread
import javax.inject.Inject

class PopulationRepository(application: QuikDataApp, formId: String) :
    BaseRepository<PopulationRow>(application) {

    @Inject
    lateinit var populationRowDao: PopulationRowDao

    private val mPopulation: LiveData<List<PopulationRow>>

    init {
        DaggerPopulationRepositoryComponent
            .builder()
            .databaseModule(DatabaseModule(application))
            .build()
            .inject(this)

        mPopulation = populationRowDao.getByFormId(formId)
    }

    val population: LiveData<List<PopulationRow>>
        get() = mPopulation

    override fun updateData(data: PopulationRow) {
        runOnIoThread {
            populationRowDao.update(data)
        }
    }
}