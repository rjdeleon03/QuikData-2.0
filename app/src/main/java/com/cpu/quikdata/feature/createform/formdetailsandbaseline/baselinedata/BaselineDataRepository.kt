package com.cpu.quikdata.feature.createform.formdetailsandbaseline.baselinedata

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import com.cpu.quikdata.base.BaseRepository
import com.cpu.quikdata.data.AppDatabase
import com.cpu.quikdata.data.baselinedata.BaselineData
import com.cpu.quikdata.data.baselinedata.BaselineDataDao
import com.cpu.quikdata.data.prefilleddata.PrefilledData
import com.cpu.quikdata.data.prefilleddata.PrefilledDataDao
import com.cpu.quikdata.di.component.repository.DaggerBaselineDataRepositoryComponent
import com.cpu.quikdata.di.module.DatabaseModule
import com.cpu.quikdata.feature.QuikDataApp
import com.cpu.quikdata.utils.runOnIoThread
import javax.inject.Inject

class BaselineDataRepository(application: QuikDataApp,
                             formId: String) {

    @Inject
    lateinit var baselineDataDao: BaselineDataDao

    @Inject
    lateinit var prefilledDataDao: PrefilledDataDao

    private val mBaselineData: LiveData<BaselineData>
    private val mPrefilledData = MediatorLiveData<PrefilledData>()

    init {
        DaggerBaselineDataRepositoryComponent.builder()
            .databaseModule(DatabaseModule(application))
            .build()
            .inject(this)

        mBaselineData = baselineDataDao.getByFormId(formId)
    }

    val baselineData : LiveData<BaselineData>
        get() = mBaselineData

    val prefilledData: LiveData<PrefilledData>
        get() = mPrefilledData

    fun updateData(data: BaselineData) {
        runOnIoThread {
            mBaselineData.value?.let {
                it.copyFrom(data)
                baselineDataDao.update(it)
            }
        }
    }

    fun pullPrefilledData() {
        val source = prefilledDataDao.get()
        mPrefilledData.addSource(source) {
            mPrefilledData.value = it
            mPrefilledData.removeSource(source)
        }
    }
}