package com.cpu.quikdata.di.createform.evacuationinfo

import androidx.lifecycle.ViewModel
import com.cpu.quikdata.di.app.module.ViewModelKey
import com.cpu.quikdata.feature.createform.evacuationinfo.EvacuationInfoViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class EvacuationInfoModule {

    @Binds
    @IntoMap
    @ViewModelKey(EvacuationInfoViewModel::class)
    abstract fun bindEvacuationInfoViewModel(evacuationInfoViewModel: EvacuationInfoViewModel): ViewModel

    companion object {


    }
}