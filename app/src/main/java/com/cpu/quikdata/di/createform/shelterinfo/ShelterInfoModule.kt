package com.cpu.quikdata.di.createform.shelterinfo

import androidx.lifecycle.ViewModel
import com.cpu.quikdata.di.app.module.ViewModelKey
import com.cpu.quikdata.feature.createform.shelterinfo.housedamage.HouseDamageViewModel
import com.cpu.quikdata.feature.createform.shelterinfo.shelterassistance.ShelterAssistanceViewModel
import com.cpu.quikdata.feature.createform.shelterinfo.sheltercoping.ShelterCopingViewModel
import com.cpu.quikdata.feature.createform.shelterinfo.sheltergaps.ShelterGapsViewModel
import com.cpu.quikdata.feature.createform.shelterinfo.shelterneeds.ShelterNeedsViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ShelterInfoModule {

    @Binds
    @IntoMap
    @ViewModelKey(HouseDamageViewModel::class)
    abstract fun bindHouseDamageViewModel(houseDamageViewModel: HouseDamageViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(ShelterCopingViewModel::class)
    abstract fun bindShelterCopingViewModel(shelterCopingViewModel: ShelterCopingViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(ShelterNeedsViewModel::class)
    abstract fun bindShelterNeedsViewModel(shelterNeedsViewModel: ShelterNeedsViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(ShelterAssistanceViewModel::class)
    abstract fun bindShelterAssistanceViewModel(shelterAssistanceViewModel: ShelterAssistanceViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(ShelterGapsViewModel::class)
    abstract fun bindShelterGapsViewModel(shelterGapsViewModel: ShelterGapsViewModel): ViewModel

}