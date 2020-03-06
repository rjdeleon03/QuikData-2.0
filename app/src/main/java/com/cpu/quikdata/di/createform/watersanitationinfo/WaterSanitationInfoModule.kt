package com.cpu.quikdata.di.createform.watersanitationinfo

import androidx.lifecycle.ViewModel
import com.cpu.quikdata.di.app.module.ViewModelKey
import com.cpu.quikdata.feature.createform.watersanitationinfo.washassistance.WashAssistanceViewModel
import com.cpu.quikdata.feature.createform.watersanitationinfo.washconditions.WashConditionsViewModel
import com.cpu.quikdata.feature.createform.watersanitationinfo.washcoping.WashCopingViewModel
import com.cpu.quikdata.feature.createform.watersanitationinfo.washgaps.WashGapsViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class WaterSanitationInfoModule {

    @Binds
    @IntoMap
    @ViewModelKey(WashConditionsViewModel::class)
    abstract fun bindWashConditionsViewModel(washConditionsViewModel: WashConditionsViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(WashCopingViewModel::class)
    abstract fun bindWashCopingViewModel(washCopingViewModel: WashCopingViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(WashAssistanceViewModel::class)
    abstract fun bindWashAssistanceViewModel(washAssistanceViewModel: WashAssistanceViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(WashGapsViewModel::class)
    abstract fun bindWashGapsViewModel(washGapsViewModel: WashGapsViewModel): ViewModel
}