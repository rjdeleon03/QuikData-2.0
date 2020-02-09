package com.cpu.quikdata.feature.createform.generalinfo.infrastructuredamage

import androidx.lifecycle.LiveData
import com.cpu.quikdata.base.BaseRepository
import com.cpu.quikdata.data.generalinfo.infrastructuredamage.InfrastructureDamageRow
import com.cpu.quikdata.data.generalinfo.infrastructuredamage.InfrastructureDamageRowDao
import com.cpu.quikdata.di.component.repository.DaggerInfrastructureDamageRepositoryComponent
import com.cpu.quikdata.di.module.DatabaseModule
import com.cpu.quikdata.feature.QuikDataApp
import com.cpu.quikdata.utils.runOnIoThread
import javax.inject.Inject

class InfrastructureDamageRepository(application: QuikDataApp, formId: String) :
        BaseRepository<InfrastructureDamageRow>(application) {

    @Inject
    lateinit var infrastructureDamageRowDao: InfrastructureDamageRowDao

    private val mInfrastructureDamage: LiveData<List<InfrastructureDamageRow>>

    init {
        DaggerInfrastructureDamageRepositoryComponent
            .builder()
            .databaseModule(DatabaseModule(application))
            .build()
            .inject(this)

        mInfrastructureDamage = infrastructureDamageRowDao.getByFormId(formId)
    }

    val infrastructureDamage: LiveData<List<InfrastructureDamageRow>>
        get() = mInfrastructureDamage

    override fun updateData(data: InfrastructureDamageRow) {
        runOnIoThread {
           infrastructureDamageRowDao.update(data)
        }
    }

}