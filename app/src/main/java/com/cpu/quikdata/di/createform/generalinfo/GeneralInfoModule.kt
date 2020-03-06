package com.cpu.quikdata.di.createform.generalinfo

import androidx.lifecycle.ViewModel
import com.cpu.quikdata.di.app.module.ViewModelKey
import com.cpu.quikdata.feature.createform.generalinfo.calamityinfo.CalamityInfoViewModel
import com.cpu.quikdata.feature.createform.generalinfo.casualties.CasualtiesViewModel
import com.cpu.quikdata.feature.createform.generalinfo.causeofdeath.CauseOfDeathViewModel
import com.cpu.quikdata.feature.createform.generalinfo.families.FamiliesViewModel
import com.cpu.quikdata.feature.createform.generalinfo.infrastructuredamage.InfrastructureDamageViewModel
import com.cpu.quikdata.feature.createform.generalinfo.population.PopulationViewModel
import com.cpu.quikdata.feature.createform.generalinfo.vulnerable.VulnerableViewModel
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

    @Binds
    @IntoMap
    @ViewModelKey(VulnerableViewModel::class)
    abstract fun bindVulnerableViewModel(vulnerableViewModel: VulnerableViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(CasualtiesViewModel::class)
    abstract fun bindCasualtiesViewModel(casualtiesViewModel: CasualtiesViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(CauseOfDeathViewModel::class)
    abstract fun bindCauseOfDeathViewModel(causeOfDeathViewModel: CauseOfDeathViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(InfrastructureDamageViewModel::class)
    abstract fun bindInfrastructureDamageViewModel(infrastructureDamageViewModel: InfrastructureDamageViewModel): ViewModel

}