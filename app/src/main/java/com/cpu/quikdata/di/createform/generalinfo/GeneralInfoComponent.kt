package com.cpu.quikdata.di.createform.generalinfo

import com.cpu.quikdata.feature.createform.generalinfo.calamityinfo.CalamityInfoFragment
import com.cpu.quikdata.feature.createform.generalinfo.casualties.CasualtiesFragment
import com.cpu.quikdata.feature.createform.generalinfo.causeofdeath.CauseOfDeathFragment
import com.cpu.quikdata.feature.createform.generalinfo.families.FamiliesFragment
import com.cpu.quikdata.feature.createform.generalinfo.infrastructuredamage.InfrastructureDamageFragment
import com.cpu.quikdata.feature.createform.generalinfo.population.PopulationFragment
import com.cpu.quikdata.feature.createform.generalinfo.vulnerable.VulnerableFragment
import dagger.Subcomponent

@Subcomponent(modules = [GeneralInfoModule::class])
interface GeneralInfoComponent {

    @Subcomponent.Factory
    interface Factory {
        fun create(): GeneralInfoComponent
    }

    fun inject(calamityInfoFragment: CalamityInfoFragment)
    fun inject(populationFragment: PopulationFragment)
    fun inject(familiesFragment: FamiliesFragment)
    fun inject(vulnerableFragment: VulnerableFragment)
    fun inject(casualtiesFragment: CasualtiesFragment)
    fun inject(causeOfDeathFragment: CauseOfDeathFragment)
    fun inject(infrastructureDamageFragment: InfrastructureDamageFragment)
}