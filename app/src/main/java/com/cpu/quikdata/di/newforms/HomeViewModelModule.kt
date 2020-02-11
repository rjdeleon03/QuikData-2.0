package com.cpu.quikdata.di.newforms

import androidx.lifecycle.ViewModel
import com.cpu.quikdata.data.AppDatabase
import com.cpu.quikdata.di.annotation.ViewModelKey
import com.cpu.quikdata.feature.main.home.HomeViewModel
import com.cpu.quikdata.feature.main.newforms.NewFormsRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap

@Module
abstract class HomeViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(HomeViewModel::class)
    abstract fun bindsHomeViewModel(homeViewModel: HomeViewModel): ViewModel

    companion object {
        @Provides
        fun provideNewFormsRepository(database: AppDatabase): NewFormsRepository
                = NewFormsRepository(database)
    }
}