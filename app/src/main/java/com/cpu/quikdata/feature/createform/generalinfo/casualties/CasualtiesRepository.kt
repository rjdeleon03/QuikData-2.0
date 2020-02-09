package com.cpu.quikdata.feature.createform.generalinfo.casualties

import androidx.lifecycle.LiveData
import com.cpu.quikdata.data.generalinfo.casualtiesrow.CasualtiesRow
import com.cpu.quikdata.data.generalinfo.casualtiesrow.CasualtiesRowDao
import com.cpu.quikdata.di.component.repository.DaggerCasualtiesRepositoryComponent
import com.cpu.quikdata.di.module.DatabaseModule
import com.cpu.quikdata.feature.QuikDataApp
import com.cpu.quikdata.utils.runOnIoThread
import javax.inject.Inject

class CasualtiesRepository(application: QuikDataApp, formId: String) {

    @Inject
    lateinit var casualtiesRowDao: CasualtiesRowDao

    private val mCasualties: LiveData<List<CasualtiesRow>>

    init {
        DaggerCasualtiesRepositoryComponent
            .builder()
            .databaseModule(DatabaseModule(application))
            .build()
            .inject(this)

        mCasualties = casualtiesRowDao.getByFormId(formId)
    }

    val casualties: LiveData<List<CasualtiesRow>>
        get() = mCasualties

    fun updateData(data: CasualtiesRow) {
        runOnIoThread {
            casualtiesRowDao.update(data)
        }
    }
}