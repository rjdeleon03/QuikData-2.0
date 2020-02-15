package com.cpu.quikdata.di.module.viewmodel

import androidx.lifecycle.ViewModelProvider
import dagger.Binds
import dagger.Module
import dagger.Reusable

@Module
abstract class ViewModelFactoryModule {

    @Binds
    @Reusable
    abstract fun bindViewModelFactory(providerFactory: ViewModelProviderFactory): ViewModelProvider.Factory


}