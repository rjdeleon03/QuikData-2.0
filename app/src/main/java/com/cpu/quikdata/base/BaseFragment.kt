package com.cpu.quikdata.base

import androidx.fragment.app.Fragment
import com.cpu.quikdata.di.app.AppComponent
import com.cpu.quikdata.di.app.module.DaggerViewModelFactory
import com.cpu.quikdata.feature.QuikDataApp
import javax.inject.Inject

abstract class BaseFragment: Fragment() {

    @Inject
    lateinit var mViewModelFactory: DaggerViewModelFactory

    val mAppComponent: AppComponent
        get() = (requireActivity().application as QuikDataApp).appComponent
}