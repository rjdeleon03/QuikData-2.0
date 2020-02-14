package com.cpu.quikdata.di.module

import com.cpu.quikdata.di.annotation.ActivityScope
import com.cpu.quikdata.di.module.createform.CreateFormFragmentsModule
import com.cpu.quikdata.di.module.createform.CreateFormSharedModule
import com.cpu.quikdata.feature.createform.CreateFormActivity
import com.cpu.quikdata.feature.main.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
 * Builds all activities
 */
@Module
abstract class ActivityBuilderModule {

    @ContributesAndroidInjector
    abstract fun contributeMainActivity(): MainActivity

    @ActivityScope
    @ContributesAndroidInjector(
        modules = [CreateFormSharedModule::class, CreateFormFragmentsModule::class]
    )
    abstract fun contributeCreateFormActivity(): CreateFormActivity
}