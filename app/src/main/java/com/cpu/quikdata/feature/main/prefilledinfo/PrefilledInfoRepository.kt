package com.cpu.quikdata.feature.main.prefilledinfo

import androidx.lifecycle.LiveData
import com.cpu.quikdata.data.AppDatabase
import com.cpu.quikdata.data.prefilleddata.PrefilledData
import javax.inject.Inject

class PrefilledInfoRepository @Inject constructor(private val mDatabase: AppDatabase) {

    val prefilledData: LiveData<PrefilledData>
        get() = mDatabase.prefilledDataDao().get()

    suspend fun updatePrefilledData(prefilledData: PrefilledData) {
        mDatabase.prefilledDataDao().update(prefilledData)
    }
}