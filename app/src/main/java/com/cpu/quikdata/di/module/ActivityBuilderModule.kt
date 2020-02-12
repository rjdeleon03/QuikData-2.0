package com.cpu.quikdata.di.module

import com.cpu.quikdata.di.module.view.ActivityModule
import com.cpu.quikdata.di.module.viewmodel.CreateFormViewModelModule
import com.cpu.quikdata.feature.createform.CreateFormActivity
import com.cpu.quikdata.feature.main.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuilderModule {

    @ContributesAndroidInjector
    abstract fun contributeMainActivity(): MainActivity

    @ContributesAndroidInjector(
        modules = [ActivityModule::class, CreateFormViewModelModule::class]
    )
    abstract fun contributCreateFormActivity(): CreateFormActivity
}