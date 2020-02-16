package com.cpu.quikdata.feature.service

import android.util.Log
import com.cpu.quikdata.helpers.SharedPreferencesHelper
import com.google.firebase.messaging.FirebaseMessagingService
import dagger.android.AndroidInjection
import dagger.android.AndroidInjector
import dagger.android.HasAndroidInjector
import javax.inject.Inject

class QuikDataMessagingService: FirebaseMessagingService() {

    @Inject
    lateinit var mSharedPreferencesHelper: SharedPreferencesHelper

    companion object {
        private const val TAG = "QDMessagingService"
    }

    override fun onCreate() {
        AndroidInjection.inject(this)
        super.onCreate()
    }

    override fun onNewToken(token: String) {
        super.onNewToken(token)

        Log.d(TAG,"New token received: $token")
        mSharedPreferencesHelper.saveFirebaseId(token)
    }
}