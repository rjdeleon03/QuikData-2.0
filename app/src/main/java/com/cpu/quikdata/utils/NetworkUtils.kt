@file:Suppress("DEPRECATION")

package com.cpu.quikdata.utils

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.net.NetworkInfo
import android.os.Build
import com.cpu.quikdata.R
import com.cpu.quikdata.common.showToast

fun isInternetAvailable(context: Context) : Boolean {
    val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
        val nw = cm.activeNetwork ?: return false
        val actNw = cm.getNetworkCapabilities(nw) ?: return false
        return when {
            actNw.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
            actNw.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
            else -> false
        }
    } else {
        val activeNetwork: NetworkInfo? = cm.activeNetworkInfo
        return activeNetwork?.isConnected ?: false
    }
}

fun isInternetAvailableThenToast(context: Context,
                                 messageId: Int = R.string.text_error_no_internet): Boolean {
    val hasInternet = isInternetAvailable(context)
    if (!hasInternet) {
        context.showToast(messageId)
    }
    return hasInternet
}