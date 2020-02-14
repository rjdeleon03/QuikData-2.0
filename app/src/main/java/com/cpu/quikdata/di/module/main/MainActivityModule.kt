package com.cpu.quikdata.di.module.main

import android.app.Activity
import com.cpu.quikdata.di.annotation.ActivityScope
import com.cpu.quikdata.di.module.viewmodel.AssistedInjectModule
import com.cpu.quikdata.feature.main.MainActivity
import dagger.Binds
import dagger.Module

@Module(includes = [AssistedInjectModule::class])
abstract class MainActivityModule {

    @Binds
    @ActivityScope
    abstract fun bindsMainActivity(activity: MainActivity): Activity

}