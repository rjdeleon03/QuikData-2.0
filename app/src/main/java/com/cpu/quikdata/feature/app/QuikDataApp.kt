package com.cpu.quikdata.feature.app

import android.app.Service
import android.os.Build
import android.util.Log
import com.cpu.quikdata.FIREBASE_KEY_DEVICES
import com.cpu.quikdata.data.AppDatabase
import com.cpu.quikdata.data.prefilleddata.PrefilledData
import com.cpu.quikdata.di.component.DaggerAppComponent
import com.cpu.quikdata.feature.service.QuikDataMessagingService
import com.cpu.quikdata.helpers.SharedPreferencesHelper
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.iid.FirebaseInstanceId
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import net.danlew.android.joda.JodaTimeAndroid
import javax.inject.Inject
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector


class QuikDataApp : DaggerApplication(), HasAndroidInjector {

    companion object {
        private const val TAG = "QuikDataApp"
    }

    @Inject
    lateinit var mSharedPreferencesHelper: SharedPreferencesHelper

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
                mSharedPreferencesHelper.saveDeviceId(push.key)
            }
        }
    }

    @Inject
    fun setupNotifications(firebaseInstanceId: FirebaseInstanceId) {

        mSharedPreferencesHelper.getFirebaseId()?.let{
            Log.d(TAG,"New token received: $it")
            return
        }

        firebaseInstanceId.instanceId.addOnCompleteListener { task ->
            if (!task.isSuccessful) {
                Log.d(TAG, "getInstanceId failed ${task.exception}")
                return@addOnCompleteListener
            }
        }
    }

    private fun isDeviceRegistered(): Boolean {
        val deviceId = mSharedPreferencesHelper.getDeviceId()
        return !deviceId.isNullOrBlank()
    }
}