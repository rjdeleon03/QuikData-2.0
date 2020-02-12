package com.cpu.quikdata.di.module.createform

import com.cpu.quikdata.base.BaseCreateFormFragment
import com.cpu.quikdata.di.module.createform.sections.*
import com.cpu.quikdata.feature.createform.basicselection.BasicSelectionFragment
import com.cpu.quikdata.feature.createform.casestories.CaseStoriesFragment
import com.cpu.quikdata.feature.createform.formdetailsandbaseline.baselinedata.BaselineDataFragment
import com.cpu.quikdata.feature.createform.formdetailsandbaseline.formdetails.FormDetailsFragment
import com.cpu.quikdata.feature.createform.generalinfo.calamityinfo.CalamityInfoFragment
import com.cpu.quikdata.feature.createform.generalinfo.casualties.CasualtiesFragment
import com.cpu.quikdata.feature.createform.generalinfo.causeofdeath.CauseOfDeathFragment
import com.cpu.quikdata.feature.createform.generalinfo.families.FamiliesFragment
import com.cpu.quikdata.feature.createform.generalinfo.infrastructuredamage.InfrastructureDamageFragment
import com.cpu.quikdata.feature.createform.generalinfo.population.PopulationFragment
import com.cpu.quikdata.feature.createform.generalinfo.vulnerable.VulnerableFragment
import com.cpu.quikdata.feature.createform.selection.SelectionFragment
import com.cpu.quikdata.feature.createform.shelterinfo.housedamage.HouseDamageFragment
import com.cpu.quikdata.feature.createform.shelterinfo.shelterassistance.ShelterAssistanceFragment
import com.cpu.quikdata.feature.createform.shelterinfo.sheltercoping.ShelterCopingFragment
import com.cpu.quikdata.feature.createform.shelterinfo.sheltergaps.ShelterGapsFragment
import com.cpu.quikdata.feature.createform.shelterinfo.shelterneeds.ShelterNeedsFragment
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

    @ContributesAndroidInjector
    abstract fun contributeSelectionFragment(): SelectionFragment

    @ContributesAndroidInjector(modules = [FormDetailsModule::class])
    abstract fun contributeFormDetailsFragment(): FormDetailsFragment

    @ContributesAndroidInjector(modules = [BaselineDataModule::class])
    abstract fun contributeBaselineDataFragment(): BaselineDataFragment

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

    @ContributesAndroidInjector(modules = [HouseDamageModule::class])
    abstract fun contributeHouseDamageFragment(): HouseDamageFragment

    @ContributesAndroidInjector(modules = [ShelterCopingModule::class])
    abstract fun contributeShelterCopingFragment(): ShelterCopingFragment

    @ContributesAndroidInjector(modules = [ShelterNeedsModule::class])
    abstract fun contributeShelterNeedsFragment(): ShelterNeedsFragment

    @ContributesAndroidInjector(modules = [ShelterAssistanceModule::class])
    abstract fun contributeShelterAssistanceFragment(): ShelterAssistanceFragment

    @ContributesAndroidInjector(modules = [ShelterGapsModule::class])
    abstract fun contributeShelterGapsFragment(): ShelterGapsFragment

    @ContributesAndroidInjector(modules = [CaseStoriesModule::class])
    abstract fun contributeCaseStoriesFragment(): CaseStoriesFragment
}