package com.cpu.quikdata.base

import androidx.fragment.app.Fragment
import com.cpu.quikdata.di.AppComponent
import com.cpu.quikdata.feature.QuikDataApp

abstract class BaseFragment: Fragment() {

    val appComponent: AppComponent
        get() = (activity!!.application as QuikDataApp).appComponent
}