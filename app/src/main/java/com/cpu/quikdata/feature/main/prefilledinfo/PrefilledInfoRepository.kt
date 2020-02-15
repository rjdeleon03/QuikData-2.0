package com.cpu.quikdata.feature.main.prefilledinfo

import androidx.lifecycle.LiveData
import com.cpu.quikdata.data.AppDatabase
import com.cpu.quikdata.data.prefilleddata.PrefilledData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class PrefilledInfoRepository(private val mDatabase: AppDatabase) {

    val prefilledData: LiveData<PrefilledData>
        get() = mDatabase.prefilledDataDao().get()

    suspend fun updatePrefilledData(prefilledData: PrefilledData) {
        mDatabase.prefilledDataDao().update(prefilledData)
    }
}