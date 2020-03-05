package com.cpu.quikdata.base

import androidx.fragment.app.Fragment
import com.cpu.quikdata.di.app.AppComponent
import com.cpu.quikdata.feature.QuikDataApp

abstract class BaseFragment: Fragment() {

    val appComponent: AppComponent
        get() = (requireActivity().application as QuikDataApp).appComponent
}