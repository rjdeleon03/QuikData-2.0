package com.cpu.quikdata.feature

import android.app.Application
import android.os.Build
import com.cpu.quikdata.FIREBASE_KEY_DEVICES
import com.cpu.quikdata.common.SharedPreferencesHelper
import com.cpu.quikdata.data.AppDatabase
import com.cpu.quikdata.data.prefilleddata.PrefilledData
import com.cpu.quikdata.di.AppComponent
import com.cpu.quikdata.di.DaggerAppComponent
import com.cpu.quikdata.utils.runOnIoThread
import com.google.firebase.database.FirebaseDatabase
import net.danlew.android.joda.JodaTimeAndroid
import javax.inject.Inject

class QuikDataApp : Application() {

    @Inject lateinit var mSharedPrefsHelper: SharedPreferencesHelper

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
    }

    private fun setupDatabase() {
        val db = AppDatabase.get(this)
        runOnIoThread {
            db.prefilledDataDao().insert(PrefilledData())
        }
    }

    private fun setupDevice() {
        val serverRef = FirebaseDatabase.getInstance().reference.child(FIREBASE_KEY_DEVICES)
        val push = serverRef.push()
        runOnIoThread {
            val task = push.setValue("${Build.MANUFACTURER} ${Build.MODEL}")
            task.addOnCompleteListener { mSharedPrefsHelper.saveDeviceId(push.key) }
        }
    }

    private fun isDeviceRegistered(): Boolean {
        val deviceId = mSharedPrefsHelper.getDeviceId()
        return !deviceId.isNullOrBlank()
    }
}