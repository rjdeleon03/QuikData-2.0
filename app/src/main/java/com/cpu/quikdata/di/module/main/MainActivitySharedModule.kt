package com.cpu.quikdata.di.module.main

import android.app.Activity
import com.cpu.quikdata.di.annotation.ActivityScope
import com.cpu.quikdata.di.module.AssistedInjectModule
import com.cpu.quikdata.feature.main.MainActivity
import dagger.Binds
import dagger.Module

@Module(includes = [AssistedInjectModule::class])
abstract class MainActivitySharedModule {

    @Binds
    @ActivityScope
    abstract fun bindsMainActivity(activity: MainActivity): Activity

}