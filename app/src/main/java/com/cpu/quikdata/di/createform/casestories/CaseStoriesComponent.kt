package com.cpu.quikdata.di.createform.casestories

import com.cpu.quikdata.feature.createform.casestories.CaseStoriesFragment
import dagger.Subcomponent

@Subcomponent(modules = [CaseStoriesModule::class])
interface CaseStoriesComponent {

    @Subcomponent.Factory
    interface Factory {
        fun create(): CaseStoriesComponent
    }

    fun inject(caseStoriesFragment: CaseStoriesFragment)
}