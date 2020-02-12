package com.cpu.quikdata.di.module

import androidx.lifecycle.ViewModelProvider
import com.cpu.quikdata.common.ViewModelProviderFactory
import dagger.Binds
import dagger.Module
import dagger.Reusable
import javax.inject.Singleton

@Module
abstract class ViewModelFactoryModule {

    @Binds
    @Reusable
    abstract fun bindViewModelFactory(providerFactory: ViewModelProviderFactory): ViewModelProvider.Factory


}