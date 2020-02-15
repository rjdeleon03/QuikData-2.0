package com.cpu.quikdata.feature

import android.os.Build
import com.cpu.quikdata.FIREBASE_KEY_DEVICES
import com.cpu.quikdata.data.AppDatabase
import com.cpu.quikdata.data.prefilleddata.PrefilledData
import com.cpu.quikdata.di.component.DaggerAppComponent
import com.cpu.quikdata.helpers.SharedPreferencesHelper
import com.google.firebase.database.FirebaseDatabase
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
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
    }

    @Inject
    fun setupDatabase(db: AppDatabase) {
        GlobalScope.launch(Dispatchers.IO) { db.prefilledDataDao().insert(PrefilledData()) }
    }

    @Inject
    fun setupDevice(firebaseDb: FirebaseDatabase) {

        if (!isDeviceRegistered()) { return }
        val serverRef = firebaseDb.reference.child(FIREBASE_KEY_DEVICES)
        val push = serverRef.push()
        GlobalScope.launch(Dispatchers.IO) {
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