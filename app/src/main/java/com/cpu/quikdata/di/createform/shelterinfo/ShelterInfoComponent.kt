package com.cpu.quikdata.di.createform.shelterinfo

import com.cpu.quikdata.feature.createform.shelterinfo.housedamage.HouseDamageFragment
import com.cpu.quikdata.feature.createform.shelterinfo.shelterassistance.ShelterAssistanceFragment
import com.cpu.quikdata.feature.createform.shelterinfo.sheltercoping.ShelterCopingFragment
import com.cpu.quikdata.feature.createform.shelterinfo.sheltergaps.ShelterGapsFragment
import com.cpu.quikdata.feature.createform.shelterinfo.shelterneeds.ShelterNeedsFragment
import dagger.Subcomponent

@Subcomponent(modules = [ShelterInfoModule::class])
interface ShelterInfoComponent {

    @Subcomponent.Factory
    interface Factory {
        fun create(): ShelterInfoComponent
    }

    fun inject(houseDamageFragment: HouseDamageFragment)
    fun inject(shelterCopingFragment: ShelterCopingFragment)
    fun inject(shelterNeedsFragment: ShelterNeedsFragment)
    fun inject(shelterAssistanceFragment: ShelterAssistanceFragment)
    fun inject(shelterGapsFragment: ShelterGapsFragment)
}