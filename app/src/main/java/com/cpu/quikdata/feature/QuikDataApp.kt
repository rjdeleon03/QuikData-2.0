package com.cpu.quikdata.feature

import android.app.Application
import android.content.Context
import android.os.Build
import com.cpu.quikdata.DEVICE_ID_KEY
import com.cpu.quikdata.FIREBASE_KEY_DEVICES
import com.cpu.quikdata.SHARED_PREFS_KEY
import com.cpu.quikdata.data.AppDatabase
import com.cpu.quikdata.data.prefilleddata.PrefilledData
import com.cpu.quikdata.utils.runOnIoThread
import com.google.firebase.database.FirebaseDatabase
import net.danlew.android.joda.JodaTimeAndroid

class QuikDataApp : Application() {

//    var appComponent: AppComponent by lazy {
//        DaggerAppComponent.builder()
//    }

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
                val editor = getSharedPreferences(SHARED_PREFS_KEY, Context.MODE_PRIVATE).edit()
                editor.putString(DEVICE_ID_KEY, push.key)
                editor.apply()
            }
        }
    }

    private fun isDeviceRegistered(): Boolean {
        val sharedPrefs = getSharedPreferences(SHARED_PREFS_KEY, Context.MODE_PRIVATE)
        val deviceId = sharedPrefs.getString(DEVICE_ID_KEY, "")
        return !deviceId.isNullOrBlank()
    }
}