package com.cpu.quikdata.helpers

import android.content.Context
import androidx.preference.PreferenceManager
import com.cpu.quikdata.DEVICE_ID_KEY
import com.cpu.quikdata.FIREBASE_ID_KEY

class SharedPreferencesHelper(context: Context) {

    private val mPrefs = PreferenceManager.getDefaultSharedPreferences(context)

    fun saveDeviceId(deviceId: String?) {
        mPrefs.edit().putString(DEVICE_ID_KEY, deviceId).apply()
    }

    fun getDeviceId() = mPrefs.getString(DEVICE_ID_KEY, null)

    fun saveFirebaseId(firebaseId: String?) {
        mPrefs.edit().putString(FIREBASE_ID_KEY, firebaseId).apply()
    }

    fun getFirebaseId() = mPrefs.getString(FIREBASE_ID_KEY, null)
}