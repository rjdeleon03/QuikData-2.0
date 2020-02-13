package com.cpu.quikdata.di.module.viewmodel

import androidx.lifecycle.ViewModel
import com.cpu.quikdata.data.AppDatabase
import com.cpu.quikdata.di.annotation.ViewModelKey
import com.cpu.quikdata.feature.main.prefilledinfo.PrefilledInfoRepository
import com.cpu.quikdata.feature.main.prefilledinfo.PrefilledInfoViewModel
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap

@Module
abstract class PrefilledInfoViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(PrefilledInfoViewModel::class)
    abstract fun bindsPrefilledInfoViewModel(prefilledInfoViewModel: PrefilledInfoViewModel): ViewModel

    companion object {
        @Provides
        fun providePrefilledInfoRepository(database: AppDatabase): PrefilledInfoRepository
                = PrefilledInfoRepository(database)
    }
}