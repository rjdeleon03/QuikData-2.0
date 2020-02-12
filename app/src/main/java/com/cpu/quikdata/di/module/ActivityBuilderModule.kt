package com.cpu.quikdata.di.module

import com.cpu.quikdata.di.annotation.CreateFormActivityScope
import com.cpu.quikdata.di.annotation.MainActivityScope
import com.cpu.quikdata.di.module.viewmodel.CreateFormViewModelModule
import com.cpu.quikdata.feature.createform.CreateFormActivity
import com.cpu.quikdata.feature.main.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuilderModule {

    @MainActivityScope
    @ContributesAndroidInjector
    abstract fun contributeMainActivity(): MainActivity

    @CreateFormActivityScope
    @ContributesAndroidInjector(
        modules = [CreateFormViewModelModule::class]
    )
    abstract fun contributeCreateFormActivity(): CreateFormActivity
}