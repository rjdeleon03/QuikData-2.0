package com.cpu.quikdata.feature

import android.app.Application
import android.os.Build
import com.cpu.quikdata.FIREBASE_KEY_DEVICES
import com.cpu.quikdata.data.AppDatabase
import com.cpu.quikdata.data.prefilleddata.PrefilledData
import com.cpu.quikdata.data.prefilleddata.PrefilledDataDao
import com.cpu.quikdata.di.component.DaggerQuikDataAppComponent
import com.cpu.quikdata.di.module.AppModule
import com.cpu.quikdata.di.module.DatabaseModule
import com.cpu.quikdata.di.module.SharedPrefsModule
import com.cpu.quikdata.helpers.SharedPreferencesHelper
import com.cpu.quikdata.utils.runOnIoThread
import com.google.firebase.database.FirebaseDatabase
import net.danlew.android.joda.JodaTimeAndroid
import javax.inject.Inject

class QuikDataApp : Application() {

    @Inject
    lateinit var sharedPrefsHelper: SharedPreferencesHelper

    @Inject
    lateinit var databasePrefilledDao: PrefilledDataDao

    override fun onCreate() {
        super.onCreate()

        DaggerQuikDataAppComponent.builder()
            .appModule(AppModule(this))
            .databaseModule(DatabaseModule(this))
            .sharedPrefsModule(SharedPrefsModule())
            .build()
            .inject(this)

        JodaTimeAndroid.init(this)

        setupDatabase()

        if (!isDeviceRegistered()) {
            setupDevice()
        }
    }

    @Inject
    fun setupDatabase() {
        runOnIoThread {
            databasePrefilledDao.insert(PrefilledData())
        }
    }

    private fun setupDevice() {
        val serverRef = FirebaseDatabase.getInstance().reference.child(FIREBASE_KEY_DEVICES)
        val push = serverRef.push()
        runOnIoThread {
            val task = push.setValue("${Build.MANUFACTURER} ${Build.MODEL}")

            task.addOnCompleteListener {
                sharedPrefsHelper.saveDeviceId(push.key)
            }
        }
    }

    private fun isDeviceRegistered(): Boolean {
        val deviceId = sharedPrefsHelper.getDeviceId()
        return !deviceId.isNullOrBlank()
    }
}