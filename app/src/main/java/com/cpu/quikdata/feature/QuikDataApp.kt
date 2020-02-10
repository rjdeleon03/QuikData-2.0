package com.cpu.quikdata.feature

import android.content.Context
import android.os.Build
import com.cpu.quikdata.DEVICE_ID_KEY
import com.cpu.quikdata.FIREBASE_KEY_DEVICES
import com.cpu.quikdata.SHARED_PREFS_KEY
import com.cpu.quikdata.data.AppDatabase
import com.cpu.quikdata.data.prefilleddata.PrefilledData
import com.cpu.quikdata.di.component.DaggerAppComponent
import com.cpu.quikdata.helpers.SharedPreferencesHelper
import com.cpu.quikdata.utils.runOnIoThread
import com.google.firebase.database.FirebaseDatabase
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication
import net.danlew.android.joda.JodaTimeAndroid
import javax.inject.Inject

class QuikDataApp : DaggerApplication() {

    @Inject
    lateinit var sharedPreferencesHelper: SharedPreferencesHelper

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        return DaggerAppComponent.builder()
            .application(this)
            .build()
    }

    override fun onCreate() {
        super.onCreate()
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

            task.addOnCompleteListener {
                sharedPreferencesHelper.saveDeviceId(push.key)
            }
        }
    }

    private fun isDeviceRegistered(): Boolean {
        val deviceId = sharedPreferencesHelper.getDeviceId()
        return !deviceId.isNullOrBlank()
    }
}