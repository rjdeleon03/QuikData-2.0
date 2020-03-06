package com.cpu.quikdata.di.createform.healthinfo

import com.cpu.quikdata.feature.createform.healthinfo.diseases.DiseasesFragment
import com.cpu.quikdata.feature.createform.healthinfo.healthassistance.HealthAssistanceFragment
import com.cpu.quikdata.feature.createform.healthinfo.healthcoping.HealthCopingFragment
import com.cpu.quikdata.feature.createform.healthinfo.healthgaps.HealthGapsFragment
import com.cpu.quikdata.feature.createform.healthinfo.psychosocial.PsychosocialFragment
import com.cpu.quikdata.feature.createform.healthinfo.specialneeds.SpecialNeedsFragment
import dagger.Subcomponent

@Subcomponent(modules = [HealthInfoModule::class])
interface HealthInfoComponent {

    @Subcomponent.Factory
    interface Factory {
        fun create(): HealthInfoComponent
    }

    fun inject(diseasesFragment: DiseasesFragment)
    fun inject(specialNeedsFragment: SpecialNeedsFragment)
    fun inject(psychosocialFragment: PsychosocialFragment)
    fun inject(healthCopingFragment: HealthCopingFragment)
    fun inject(healthAssistanceFragment: HealthAssistanceFragment)
    fun inject(healthGapsFragment: HealthGapsFragment)
}