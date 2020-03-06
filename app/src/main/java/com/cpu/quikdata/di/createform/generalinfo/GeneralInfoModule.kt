package com.cpu.quikdata.di.createform.generalinfo

import androidx.lifecycle.ViewModel
import com.cpu.quikdata.di.app.module.ViewModelKey
import com.cpu.quikdata.feature.createform.generalinfo.calamityinfo.CalamityInfoViewModel
import com.cpu.quikdata.feature.createform.generalinfo.families.FamiliesViewModel
import com.cpu.quikdata.feature.createform.generalinfo.population.PopulationViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class GeneralInfoModule {

    @Binds
    @IntoMap
    @ViewModelKey(CalamityInfoViewModel::class)
    abstract fun bindCalamityInfoViewModel(calamityInfoViewModel: CalamityInfoViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(PopulationViewModel::class)
    abstract fun bindPopulationViewModel(populationViewModel: PopulationViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(FamiliesViewModel::class)
    abstract fun bindFamiliesViewModel(familiesViewModel: FamiliesViewModel): ViewModel

}