package com.cpu.quikdata.feature

import android.app.Application
import android.os.Build
import androidx.work.Configuration
import androidx.work.WorkManager
import androidx.work.WorkerFactory
import com.cpu.quikdata.FIREBASE_KEY_DEVICES
import com.cpu.quikdata.common.SharedPreferencesHelper
import com.cpu.quikdata.data.AppDatabase
import com.cpu.quikdata.data.prefilleddata.PrefilledData
import com.cpu.quikdata.di.app.AppComponent
import com.cpu.quikdata.di.app.DaggerAppComponent
import com.cpu.quikdata.di.app.module.ChildWorkerFactory
import com.google.firebase.database.FirebaseDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import net.danlew.android.joda.JodaTimeAndroid
import javax.inject.Inject

class QuikDataApp : Application() {

    @Inject lateinit var mSharedPrefsHelper: SharedPreferencesHelper
    @Inject lateinit var mDatabase: AppDatabase

    val appComponent: AppComponent by lazy {
        DaggerAppComponent.factory().create(this)
    }

    override fun onCreate() {
        super.onCreate()

        appComponent.inject(this)

        JodaTimeAndroid.init(this)

        setupDatabase()

        if (!isDeviceRegistered()) {
            setupDevice()
        }

        // Initialize worker factory
        val factory = appComponent.workerFactory()
        WorkManager.initialize(this,
            Configuration.Builder().setWorkerFactory(factory).build())
    }

    private fun setupDatabase() {
        GlobalScope.launch(Dispatchers.IO) {
            mDatabase.prefilledDataDao().insert(PrefilledData())
        }
    }

    private fun setupDevice() {
        val serverRef = FirebaseDatabase.getInstance().reference.child(FIREBASE_KEY_DEVICES)
        val push = serverRef.push()
        GlobalScope.launch(Dispatchers.IO) {
            val task = push.setValue("${Build.MANUFACTURER} ${Build.MODEL}")
            task.addOnCompleteListener { mSharedPrefsHelper.saveDeviceId(push.key) }
        }
    }

    private fun isDeviceRegistered(): Boolean {
        val deviceId = mSharedPrefsHelper.getDeviceId()
        return !deviceId.isNullOrBlank()
    }
}