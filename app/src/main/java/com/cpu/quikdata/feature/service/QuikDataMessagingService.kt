package com.cpu.quikdata.feature.service

import android.util.Log
import com.cpu.quikdata.R
import com.cpu.quikdata.helpers.SharedPreferencesHelper
import com.google.firebase.iid.FirebaseInstanceId
import com.google.firebase.messaging.FirebaseMessaging
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import dagger.android.AndroidInjection
import dagger.android.AndroidInjector
import dagger.android.HasAndroidInjector
import javax.inject.Inject

class QuikDataMessagingService: FirebaseMessagingService() {

    @Inject
    lateinit var mSharedPreferencesHelper: SharedPreferencesHelper

    @Inject
    lateinit var mFirebaseMessaging: FirebaseMessaging

    companion object {
        private const val TAG = "QDMessagingService"
        private const val TOPIC_EMERGENCY = "emergency"
    }

    override fun onCreate() {
        AndroidInjection.inject(this)
        super.onCreate()
    }

    override fun onNewToken(token: String) {
        super.onNewToken(token)

        Log.d(TAG,"New token received: $token")
        mSharedPreferencesHelper.saveFirebaseId(token)
        mFirebaseMessaging.subscribeToTopic(TOPIC_EMERGENCY)
            .addOnCompleteListener { task ->
            var msg = "Subscribed to $TOPIC_EMERGENCY"
            if (!task.isSuccessful) {
                msg = "Failed to subscribe to $TOPIC_EMERGENCY"
            }
            Log.d(TAG, msg)
        }
    }

    override fun onMessageReceived(remoteMessage: RemoteMessage) {
        super.onMessageReceived(remoteMessage)

        remoteMessage.notification?.let { notif ->
            notif.body?.let {
                println("NOTIF: ${notif.title} :: $it ---- ${remoteMessage.sentTime}")
            }
        }
    }
}