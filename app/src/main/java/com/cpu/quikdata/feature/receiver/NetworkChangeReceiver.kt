package com.cpu.quikdata.feature.receiver

import android.content.Context
import android.content.Intent
import com.cpu.quikdata.helpers.SharedPreferencesHelper
import com.cpu.quikdata.utils.isInternetAvailable
import com.google.firebase.iid.FirebaseInstanceId
import dagger.android.DaggerBroadcastReceiver
import javax.inject.Inject

class NetworkChangeReceiver: DaggerBroadcastReceiver() {

    companion object {
        private const val TAG = "NetworkChangeReceiver"
    }

    @Inject
    lateinit var mSharedPreferencesHelper: SharedPreferencesHelper

    @Inject
    lateinit var mFirebaseInstanceId: FirebaseInstanceId

    override fun onReceive(context: Context?, intent: Intent?) {
        super.onReceive(context, intent)

        context?.let {
            if (isInternetAvailable(it)) {
                mSharedPreferencesHelper.getFirebaseId()?.let {
                    return
                }
                mFirebaseInstanceId.instanceId
            }
        }
     }
}