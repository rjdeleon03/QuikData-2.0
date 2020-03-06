package com.cpu.quikdata.di.createform.generalinfo

import com.cpu.quikdata.feature.createform.generalinfo.calamityinfo.CalamityInfoFragment
import com.cpu.quikdata.feature.createform.generalinfo.population.PopulationFragment
import dagger.Subcomponent

@Subcomponent(modules = [GeneralInfoModule::class])
interface GeneralInfoComponent {

    @Subcomponent.Factory
    interface Factory {
        fun create(): GeneralInfoComponent
    }

    fun inject(calamityInfoFragment: CalamityInfoFragment)
    fun inject(populationFragment: PopulationFragment)
}