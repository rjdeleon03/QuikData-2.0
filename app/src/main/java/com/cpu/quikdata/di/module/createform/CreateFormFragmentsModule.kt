package com.cpu.quikdata.di.module.createform

import com.cpu.quikdata.base.BaseCreateFormFragment
import com.cpu.quikdata.di.module.createform.sections.*
import com.cpu.quikdata.feature.createform.basicselection.BasicSelectionFragment
import com.cpu.quikdata.feature.createform.generalinfo.calamityinfo.CalamityInfoFragment
import com.cpu.quikdata.feature.createform.generalinfo.casualties.CasualtiesFragment
import com.cpu.quikdata.feature.createform.generalinfo.causeofdeath.CauseOfDeathFragment
import com.cpu.quikdata.feature.createform.generalinfo.families.FamiliesFragment
import com.cpu.quikdata.feature.createform.generalinfo.infrastructuredamage.InfrastructureDamageFragment
import com.cpu.quikdata.feature.createform.generalinfo.population.PopulationFragment
import com.cpu.quikdata.feature.createform.generalinfo.vulnerable.VulnerableFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
 * Supports injection for child fragments of CreateFormActivity
 */
@Module
abstract class CreateFormFragmentsModule {

    @ContributesAndroidInjector
    abstract fun contributeBaseCreateFormFragment(): BaseCreateFormFragment

    @ContributesAndroidInjector
    abstract fun contributeBasicSelectionFragment(): BasicSelectionFragment

    @ContributesAndroidInjector(modules = [CalamityInfoModule::class])
    abstract fun contributeCalamityInfoFragment(): CalamityInfoFragment

    @ContributesAndroidInjector(modules = [PopulationModule::class])
    abstract fun contributePopulationFragment(): PopulationFragment

    @ContributesAndroidInjector(modules = [FamiliesModule::class])
    abstract fun contributeFamiliesFragment(): FamiliesFragment

    @ContributesAndroidInjector(modules = [VulnerableModule::class])
    abstract fun contributeVulnerableFragment(): VulnerableFragment

    @ContributesAndroidInjector(modules = [CasualtiesModule::class])
    abstract fun contributeCasualtiesFragment(): CasualtiesFragment

    @ContributesAndroidInjector(modules = [CauseOfDeathModule::class])
    abstract fun contributeCauseOfDeathFragment(): CauseOfDeathFragment

    @ContributesAndroidInjector(modules = [InfrastructureDamageModule::class])
    abstract fun contributeInfrastructureDamageFragment(): InfrastructureDamageFragment
}