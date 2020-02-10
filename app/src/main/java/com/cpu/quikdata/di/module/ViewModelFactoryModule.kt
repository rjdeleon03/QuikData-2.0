package com.cpu.quikdata.di.module

import androidx.lifecycle.ViewModelProvider
import com.cpu.quikdata.common.ViewModelProviderFactory
import dagger.Binds
import dagger.Module

@Module
abstract class ViewModelFactoryModule {

    @Binds
    abstract fun bindViewModelFactory(providerFactory: ViewModelProviderFactory): ViewModelProvider.Factory


}