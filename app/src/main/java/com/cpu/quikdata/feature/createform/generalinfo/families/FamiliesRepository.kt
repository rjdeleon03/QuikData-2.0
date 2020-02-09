package com.cpu.quikdata.feature.createform.generalinfo.families

import androidx.lifecycle.LiveData
import com.cpu.quikdata.data.generalinfo.families.Families
import com.cpu.quikdata.data.generalinfo.families.FamiliesDao
import com.cpu.quikdata.di.component.repository.DaggerFamiliesRepositoryComponent
import com.cpu.quikdata.di.module.DatabaseModule
import com.cpu.quikdata.feature.QuikDataApp
import com.cpu.quikdata.utils.runOnIoThread
import javax.inject.Inject

class FamiliesRepository(application: QuikDataApp, formId: String) {

    @Inject
    lateinit var familiesDao: FamiliesDao

    private val mFamilies: LiveData<Families>

    init {
        DaggerFamiliesRepositoryComponent
            .builder()
            .databaseModule(DatabaseModule(application))
            .build()
            .inject(this)

        mFamilies = familiesDao.getByFormId(formId)
    }

    val families: LiveData<Families>
        get() = mFamilies

    fun updateData(data: Families) {
        runOnIoThread {
            mFamilies.value?.let {
                it.copyFrom(data)
                familiesDao.update(it)
            }
        }
    }
}