package com.cpu.quikdata.di.createform.healthinfo

import androidx.lifecycle.ViewModel
import com.cpu.quikdata.di.app.module.ViewModelKey
import com.cpu.quikdata.feature.createform.healthinfo.diseases.DiseasesViewModel
import com.cpu.quikdata.feature.createform.healthinfo.healthassistance.HealthAssistanceViewModel
import com.cpu.quikdata.feature.createform.healthinfo.healthcoping.HealthCopingViewModel
import com.cpu.quikdata.feature.createform.healthinfo.healthgaps.HealthGapsViewModel
import com.cpu.quikdata.feature.createform.healthinfo.psychosocial.PsychosocialViewModel
import com.cpu.quikdata.feature.createform.healthinfo.specialneeds.SpecialNeedsViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class HealthInfoModule {

    @Binds
    @IntoMap
    @ViewModelKey(DiseasesViewModel::class)
    abstract fun bindDiseasesViewModel(diseasesViewModel: DiseasesViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(SpecialNeedsViewModel::class)
    abstract fun bindSpecialNeedsViewModel(specialNeedsViewModel: SpecialNeedsViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(PsychosocialViewModel::class)
    abstract fun bindPsychosocialViewModel(psychosocialViewModel: PsychosocialViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(HealthCopingViewModel::class)
    abstract fun bindHealthCopingViewModel(healthCopingViewModel: HealthCopingViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(HealthAssistanceViewModel::class)
    abstract fun bindHealthAssistanceViewModel(healthAssistanceViewModel: HealthAssistanceViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(HealthGapsViewModel::class)
    abstract fun bindHealthGapsViewModel(healthGapsViewModel: HealthGapsViewModel): ViewModel
}