package com.cpu.quikdata.base

import androidx.appcompat.app.AppCompatActivity
import com.cpu.quikdata.di.app.AppComponent
import com.cpu.quikdata.feature.QuikDataApp

abstract class BaseActivity: AppCompatActivity() {

    val appComponent: AppComponent
        get() = (application as QuikDataApp).appComponent
}