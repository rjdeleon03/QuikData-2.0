package com.cpu.quikdata.utils

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

fun runOnIoThread(method: () -> Unit) {
    CoroutineScope(Job() + Dispatchers.Main).launch(Dispatchers.IO) {
        method.invoke()
    }
}

fun runOnMainThread(method: () -> Unit) {
    CoroutineScope(Job() + Dispatchers.Main).launch {
        method.invoke()
    }
}