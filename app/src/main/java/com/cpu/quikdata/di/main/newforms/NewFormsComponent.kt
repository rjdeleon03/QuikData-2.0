package com.cpu.quikdata.di.main.newforms

import com.cpu.quikdata.di.FragmentScope
import com.cpu.quikdata.feature.main.home.HomeFragment
import com.cpu.quikdata.feature.main.newforms.NewFormsFragment
import dagger.Subcomponent

@FragmentScope
@Subcomponent(modules = [NewFormsModule::class])
interface NewFormsComponent {

    @Subcomponent.Factory
    interface Factory {
        fun create(): NewFormsComponent
    }

    fun inject(fragment: NewFormsFragment)
    fun inject(fragment: HomeFragment)
}