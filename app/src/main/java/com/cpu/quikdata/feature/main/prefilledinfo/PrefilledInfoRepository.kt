package com.cpu.quikdata.feature.main.prefilledinfo

import androidx.lifecycle.LiveData
import com.cpu.quikdata.data.AppDatabase
import com.cpu.quikdata.data.prefilleddata.PrefilledData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import javax.inject.Inject

class PrefilledInfoRepository @Inject constructor(private val mDatabase: AppDatabase) {

    val prefilledData: LiveData<PrefilledData>
        get() = mDatabase.prefilledDataDao().get()

    fun updatePrefilledData(prefilledData: PrefilledData) {
        CoroutineScope(Job() + Dispatchers.Main).launch(Dispatchers.IO) {
            mDatabase.prefilledDataDao().update(prefilledData)
        }
    }
}