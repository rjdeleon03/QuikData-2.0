package com.cpu.quikdata.di.app.module

import androidx.lifecycle.ViewModel
import com.cpu.quikdata.feature.main.prefilledinfo.PrefilledInfoViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class PrefilledInfoModule {

    @Binds
    @IntoMap
    @ViewModelKey(PrefilledInfoViewModel::class)
    abstract fun bindPrefilledInfoViewModel(prefilledInfoViewModel: PrefilledInfoViewModel): ViewModel
}