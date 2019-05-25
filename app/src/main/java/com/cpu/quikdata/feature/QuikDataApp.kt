package com.cpu.quikdata.feature

import android.app.Application
import com.cpu.quikdata.data.AppDatabase
import com.cpu.quikdata.data.prefilleddata.PrefilledData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class QuikDataApp : Application() {

    override fun onCreate() {
        super.onCreate()

        /* Setup database */
        val db = AppDatabase.get(this)
        CoroutineScope(Job() + Dispatchers.Main)
            .launch(Dispatchers.IO) {
                db.prefilledDataDao().insert(PrefilledData())
            }
    }
}