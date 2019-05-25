package com.cpu.quikdata.feature.main.prefilledinfo

import android.app.Application
import androidx.lifecycle.LiveData
import com.cpu.quikdata.data.AppDatabase
import com.cpu.quikdata.data.PrefilledData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class PrefilledInfoRepository(application: Application) {

    private val mDatabase = AppDatabase.get(application)

    val prefilledData: LiveData<PrefilledData>
        get() = mDatabase.prefilledDataDao().get()

    fun updatePrefilledData(prefilledData: PrefilledData) {
        CoroutineScope(Job() + Dispatchers.Main).launch(Dispatchers.IO) {
            mDatabase.prefilledDataDao().update(prefilledData)
        }
    }
}