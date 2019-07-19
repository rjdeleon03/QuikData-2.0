package com.cpu.quikdata.utils

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import com.cpu.quikdata.R
import com.cpu.quikdata.common.showToast

fun isInternetAvailable(context: Context) : Boolean {
    val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    val activeNetwork: NetworkInfo? = cm.activeNetworkInfo
    return activeNetwork?.isConnected == true
}

fun isInternetAvailableThenToast(context: Context,
                                 messageId: Int = R.string.text_error_no_internet): Boolean {
    val hasInternet = isInternetAvailable(context)
    if (!hasInternet) {
        context.showToast(messageId)
    }
    return hasInternet
}