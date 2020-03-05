package com.cpu.quikdata.di.component.main

import com.cpu.quikdata.di.FragmentScope
import com.cpu.quikdata.di.app.module.PrefilledInfoModule
import com.cpu.quikdata.feature.main.prefilledinfo.PrefilledInfoFragment
import dagger.Subcomponent

@FragmentScope
@Subcomponent(modules = [PrefilledInfoModule::class])
interface PrefilledInfoComponent {

    @Subcomponent.Factory
    interface Factory {
        fun create(): PrefilledInfoComponent
    }

    fun inject(fragment: PrefilledInfoFragment)
}