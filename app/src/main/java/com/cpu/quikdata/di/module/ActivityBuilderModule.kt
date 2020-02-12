package com.cpu.quikdata.di.module

import com.cpu.quikdata.di.annotation.CreateFormActivityScope
import com.cpu.quikdata.di.module.createform.CreateFormActivityModule
import com.cpu.quikdata.di.module.createform.CreateFormModule
import com.cpu.quikdata.di.module.createform.CreateFormViewModelModule
import com.cpu.quikdata.feature.createform.CreateFormActivity
import com.cpu.quikdata.feature.main.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuilderModule {

    @ContributesAndroidInjector
    abstract fun contributeMainActivity(): MainActivity

    @CreateFormActivityScope
    @ContributesAndroidInjector(
        modules = [CreateFormActivityModule::class, CreateFormViewModelModule::class, CreateFormModule::class]
    )
    abstract fun contributeCreateFormActivity(): CreateFormActivity
}