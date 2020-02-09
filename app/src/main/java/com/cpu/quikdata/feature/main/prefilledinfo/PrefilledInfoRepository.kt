package com.cpu.quikdata.feature.main.prefilledinfo

import androidx.lifecycle.LiveData
import com.cpu.quikdata.data.prefilleddata.PrefilledData
import com.cpu.quikdata.data.prefilleddata.PrefilledDataDao
import com.cpu.quikdata.di.component.repository.DaggerPrefilledInfoRepositoryComponent
import com.cpu.quikdata.di.module.DatabaseModule
import com.cpu.quikdata.feature.QuikDataApp
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import javax.inject.Inject

class PrefilledInfoRepository(application: QuikDataApp) {

    @Inject
    lateinit var prefilledDataDao: PrefilledDataDao

    init {
        DaggerPrefilledInfoRepositoryComponent.builder()
            .databaseModule(DatabaseModule(application))
            .build()
            .inject(this)
    }

    val prefilledData: LiveData<PrefilledData>
        get() = prefilledDataDao.get()

    fun updatePrefilledData(prefilledData: PrefilledData) {
        CoroutineScope(Job() + Dispatchers.Main).launch(Dispatchers.IO) {
            prefilledDataDao.update(prefilledData)
        }
    }
}