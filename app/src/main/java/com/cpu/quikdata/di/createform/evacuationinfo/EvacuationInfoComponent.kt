package com.cpu.quikdata.di.createform.evacuationinfo

import com.cpu.quikdata.feature.createform.evacuationinfo.EvacuationInfoFragment
import dagger.Subcomponent

@Subcomponent(modules = [
    EvacuationInfoModule::class,
    EvacuationInfoSubcomponents::class
])
interface EvacuationInfoComponent {

    @Subcomponent.Factory
    interface Factory {
        fun create(): EvacuationInfoComponent
    }

    fun inject(evacuationInfoFragment: EvacuationInfoFragment)
}