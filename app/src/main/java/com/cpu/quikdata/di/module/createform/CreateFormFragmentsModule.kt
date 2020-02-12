package com.cpu.quikdata.di.module.createform

import com.cpu.quikdata.base.BaseCreateFormFragment
import com.cpu.quikdata.di.module.createform.sections.*
import com.cpu.quikdata.feature.createform.basicselection.BasicSelectionFragment
import com.cpu.quikdata.feature.createform.casestories.CaseStoriesFragment
import com.cpu.quikdata.feature.createform.foodsecurityinfo.foodsecurityassistance.FoodSecurityAssistanceFragment
import com.cpu.quikdata.feature.createform.foodsecurityinfo.foodsecuritycoping.FoodSecurityCopingFragment
import com.cpu.quikdata.feature.createform.foodsecurityinfo.foodsecuritygaps.FoodSecurityGapsFragment
import com.cpu.quikdata.feature.createform.foodsecurityinfo.foodsecurityimpact.FoodSecurityImpactFragment
import com.cpu.quikdata.feature.createform.foodsecurityinfo.foodsecurityneeds.FoodSecurityNeedsFragment
import com.cpu.quikdata.feature.createform.formdetailsandbaseline.baselinedata.BaselineDataFragment
import com.cpu.quikdata.feature.createform.formdetailsandbaseline.formdetails.FormDetailsFragment
import com.cpu.quikdata.feature.createform.generalinfo.calamityinfo.CalamityInfoFragment
import com.cpu.quikdata.feature.createform.generalinfo.casualties.CasualtiesFragment
import com.cpu.quikdata.feature.createform.generalinfo.causeofdeath.CauseOfDeathFragment
import com.cpu.quikdata.feature.createform.generalinfo.families.FamiliesFragment
import com.cpu.quikdata.feature.createform.generalinfo.infrastructuredamage.InfrastructureDamageFragment
import com.cpu.quikdata.feature.createform.generalinfo.population.PopulationFragment
import com.cpu.quikdata.feature.createform.generalinfo.vulnerable.VulnerableFragment
import com.cpu.quikdata.feature.createform.livelihoodsinfo.estimateddamage.EstimatedDamageFragment
import com.cpu.quikdata.feature.createform.livelihoodsinfo.incomeafter.IncomeAfterFragment
import com.cpu.quikdata.feature.createform.livelihoodsinfo.incomebefore.IncomeBeforeFragment
import com.cpu.quikdata.feature.createform.livelihoodsinfo.livelihoodsassistance.LivelihoodsAssistanceFragment
import com.cpu.quikdata.feature.createform.livelihoodsinfo.livelihoodscoping.LivelihoodsCopingFragment
import com.cpu.quikdata.feature.createform.livelihoodsinfo.livelihoodsgaps.LivelihoodsGapsFragment
import com.cpu.quikdata.feature.createform.livelihoodsinfo.livelihoodsneeds.LivelihoodsNeedsFragment
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

    @ContributesAndroidInjector(modules = [FoodSecurityImpactModule::class])
    abstract fun contributeFoodSecurityImpactsFragment(): FoodSecurityImpactFragment

    @ContributesAndroidInjector(modules = [FoodSecurityCopingModule::class])
    abstract fun contributeFoodSecurityCopingFragment(): FoodSecurityCopingFragment

    @ContributesAndroidInjector(modules = [FoodSecurityNeedsModule::class])
    abstract fun contributeFoodSecurityNeedsFragment(): FoodSecurityNeedsFragment

    @ContributesAndroidInjector(modules = [FoodSecurityAssistanceModule::class])
    abstract fun contributeFoodSecurityAssistanceFragment(): FoodSecurityAssistanceFragment

    @ContributesAndroidInjector(modules = [FoodSecurityGapsModule::class])
    abstract fun contributeFoodSecurityGapsFragment(): FoodSecurityGapsFragment

    @ContributesAndroidInjector(modules = [IncomeBeforeModule::class])
    abstract fun contributeIncomeBeforeFragment(): IncomeBeforeFragment

    @ContributesAndroidInjector(modules = [IncomeAfterModule::class])
    abstract fun contributeIncomeAfterFragment(): IncomeAfterFragment

    @ContributesAndroidInjector(modules = [EstimatedDamageModule::class])
    abstract fun contributeEstimatedDamageFragment(): EstimatedDamageFragment

    @ContributesAndroidInjector(modules = [LivelihoodsCopingModule::class])
    abstract fun contributeLivelihoodsCopingFragment(): LivelihoodsCopingFragment

    @ContributesAndroidInjector(modules = [LivelihoodsNeedsModule::class])
    abstract fun contributeLivelihoodsNeedsFragment(): LivelihoodsNeedsFragment

    @ContributesAndroidInjector(modules = [LivelihoodsAssistanceModule::class])
    abstract fun contributeLivelihoodsAssistanceFragment(): LivelihoodsAssistanceFragment

    @ContributesAndroidInjector(modules = [LivelihoodsGapsModule::class])
    abstract fun contributeLivelihoodsGapsFragment(): LivelihoodsGapsFragment

    @ContributesAndroidInjector(modules = [CaseStoriesModule::class])
    abstract fun contributeCaseStoriesFragment(): CaseStoriesFragment
}