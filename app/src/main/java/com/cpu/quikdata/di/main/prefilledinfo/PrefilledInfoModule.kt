package com.cpu.quikdata.di.main.prefilledinfo

import androidx.lifecycle.ViewModel
import com.cpu.quikdata.di.app.module.ViewModelKey
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