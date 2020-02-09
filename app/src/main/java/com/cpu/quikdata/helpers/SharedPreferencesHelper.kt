package com.cpu.quikdata.helpers

import android.content.Context
import androidx.preference.PreferenceManager
import com.cpu.quikdata.DEVICE_ID_KEY

class SharedPreferencesHelper(context: Context) {

    private val mPrefs = PreferenceManager.getDefaultSharedPreferences(context)

    fun saveDeviceId(deviceId: String?) {
        mPrefs.edit().putString(DEVICE_ID_KEY, deviceId).apply()
    }

    fun getDeviceId() = mPrefs.getString(DEVICE_ID_KEY, null)
}