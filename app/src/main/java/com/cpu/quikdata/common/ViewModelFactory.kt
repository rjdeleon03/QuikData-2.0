package com.cpu.quikdata.common

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.cpu.quikdata.feature.createform.activity.CreateFormViewModel
import com.cpu.quikdata.feature.createform.casestories.CaseStoriesViewModel
import com.cpu.quikdata.feature.createform.evacuationinfo.EvacuationInfoViewModel
import com.cpu.quikdata.feature.createform.evacuationinfo.evacuationage.EvacuationAgeViewModel
import com.cpu.quikdata.feature.createform.evacuationinfo.evacuationcoping.EvacuationCopingViewModel
import com.cpu.quikdata.feature.createform.evacuationinfo.evacuationfacilities.EvacuationFacilitiesViewModel
import com.cpu.quikdata.feature.createform.evacuationinfo.evacuationprotection.EvacuationProtectionViewModel
import com.cpu.quikdata.feature.createform.evacuationinfo.evacuationwash.EvacuationWashViewModel
import com.cpu.quikdata.feature.createform.evacuationinfo.siteinfo.SiteInfoViewModel
import com.cpu.quikdata.feature.createform.foodsecurityinfo.foodsecurityassistance.FoodSecurityAssistanceViewModel
import com.cpu.quikdata.feature.createform.foodsecurityinfo.foodsecuritycoping.FoodSecurityCopingViewModel
import com.cpu.quikdata.feature.createform.foodsecurityinfo.foodsecuritygaps.FoodSecurityGapsViewModel
import com.cpu.quikdata.feature.createform.foodsecurityinfo.foodsecurityimpact.FoodSecurityImpactViewModel
import com.cpu.quikdata.feature.createform.foodsecurityinfo.foodsecurityneeds.FoodSecurityNeedsViewModel
import com.cpu.quikdata.feature.createform.formdetailsandbaseline.baselinedata.BaselineDataViewModel
import com.cpu.quikdata.feature.createform.formdetailsandbaseline.formdetails.FormDetailsViewModel
import com.cpu.quikdata.feature.createform.generalinfo.calamityinfo.CalamityInfoViewModel
import com.cpu.quikdata.feature.createform.generalinfo.casualties.CasualtiesViewModel
import com.cpu.quikdata.feature.createform.generalinfo.causeofdeath.CauseOfDeathViewModel
import com.cpu.quikdata.feature.createform.generalinfo.families.FamiliesViewModel
import com.cpu.quikdata.feature.createform.generalinfo.infrastructuredamage.InfrastructureDamageViewModel
import com.cpu.quikdata.feature.createform.generalinfo.population.PopulationViewModel
import com.cpu.quikdata.feature.createform.generalinfo.vulnerable.VulnerableViewModel
import com.cpu.quikdata.feature.createform.healthinfo.diseases.DiseasesViewModel
import com.cpu.quikdata.feature.createform.healthinfo.healthassistance.HealthAssistanceViewModel
import com.cpu.quikdata.feature.createform.healthinfo.healthcoping.HealthCopingViewModel
import com.cpu.quikdata.feature.createform.healthinfo.healthgaps.HealthGapsViewModel
import com.cpu.quikdata.feature.createform.healthinfo.psychosocial.PsychosocialViewModel
import com.cpu.quikdata.feature.createform.healthinfo.specialneeds.SpecialNeedsViewModel
import com.cpu.quikdata.feature.createform.livelihoodsinfo.estimateddamage.EstimatedDamageViewModel
import com.cpu.quikdata.feature.createform.livelihoodsinfo.incomeafter.IncomeAfterViewModel
import com.cpu.quikdata.feature.createform.livelihoodsinfo.incomebefore.IncomeBeforeViewModel
import com.cpu.quikdata.feature.createform.livelihoodsinfo.livelihoodsassistance.LivelihoodsAssistanceViewModel
import com.cpu.quikdata.feature.createform.livelihoodsinfo.livelihoodscoping.LivelihoodsCopingViewModel
import com.cpu.quikdata.feature.createform.livelihoodsinfo.livelihoodsgaps.LivelihoodsGapsViewModel
import com.cpu.quikdata.feature.createform.livelihoodsinfo.livelihoodsneeds.LivelihoodsNeedsViewModel
import com.cpu.quikdata.feature.createform.shelterinfo.housedamage.HouseDamageViewModel
import com.cpu.quikdata.feature.createform.shelterinfo.shelterassistance.ShelterAssistanceViewModel
import com.cpu.quikdata.feature.createform.shelterinfo.sheltercoping.ShelterCopingViewModel
import com.cpu.quikdata.feature.createform.shelterinfo.sheltergaps.ShelterGapsViewModel
import com.cpu.quikdata.feature.createform.shelterinfo.shelterneeds.ShelterNeedsViewModel
import com.cpu.quikdata.feature.createform.watersanitationinfo.washassistance.WashAssistanceViewModel
import com.cpu.quikdata.feature.createform.watersanitationinfo.washconditions.WashConditionsViewModel
import com.cpu.quikdata.feature.createform.watersanitationinfo.washcoping.WashCopingViewModel
import com.cpu.quikdata.feature.createform.watersanitationinfo.washgaps.WashGapsViewModel

@Suppress("UNCHECKED_CAST")
class ViewModelFactory(application: Application, formId: String) :
    ViewModelProvider.AndroidViewModelFactory(application) {

    private val mApplication = application
    private val mFormId = formId

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {

        return when (modelClass) {
            VulnerableViewModel::class.java -> VulnerableViewModel(mApplication, mFormId) as T
            CasualtiesViewModel::class.java -> CasualtiesViewModel(mApplication, mFormId) as T
            CauseOfDeathViewModel::class.java -> CauseOfDeathViewModel(mApplication, mFormId) as T
            InfrastructureDamageViewModel::class.java -> InfrastructureDamageViewModel(mApplication, mFormId) as T
            HouseDamageViewModel::class.java -> HouseDamageViewModel(mApplication, mFormId) as T
            ShelterCopingViewModel::class.java -> ShelterCopingViewModel(mApplication, mFormId) as T
            ShelterNeedsViewModel::class.java -> ShelterNeedsViewModel(mApplication, mFormId) as T
            ShelterAssistanceViewModel::class.java -> ShelterAssistanceViewModel(mApplication, mFormId) as T
            ShelterGapsViewModel::class.java -> ShelterGapsViewModel(mApplication, mFormId) as T
            FoodSecurityImpactViewModel::class.java -> FoodSecurityImpactViewModel(mApplication, mFormId) as T
            FoodSecurityCopingViewModel::class.java -> FoodSecurityCopingViewModel(mApplication, mFormId) as T
            FoodSecurityNeedsViewModel::class.java -> FoodSecurityNeedsViewModel(mApplication, mFormId) as T
            FoodSecurityAssistanceViewModel::class.java -> FoodSecurityAssistanceViewModel(mApplication, mFormId) as T
            FoodSecurityGapsViewModel::class.java -> FoodSecurityGapsViewModel(mApplication, mFormId) as T
            IncomeBeforeViewModel::class.java -> IncomeBeforeViewModel(mApplication, mFormId) as T
            IncomeAfterViewModel::class.java -> IncomeAfterViewModel(mApplication, mFormId) as T
            EstimatedDamageViewModel::class.java -> EstimatedDamageViewModel(mApplication, mFormId) as T
            LivelihoodsCopingViewModel::class.java -> LivelihoodsCopingViewModel(mApplication, mFormId) as T
            LivelihoodsNeedsViewModel::class.java -> LivelihoodsNeedsViewModel(mApplication, mFormId) as T
            LivelihoodsAssistanceViewModel::class.java -> LivelihoodsAssistanceViewModel(mApplication, mFormId) as T
            LivelihoodsGapsViewModel::class.java -> LivelihoodsGapsViewModel(mApplication, mFormId) as T
            DiseasesViewModel::class.java -> DiseasesViewModel(mApplication, mFormId) as T
            SpecialNeedsViewModel::class.java -> SpecialNeedsViewModel(mApplication, mFormId) as T
            PsychosocialViewModel::class.java -> PsychosocialViewModel(mApplication, mFormId) as T
            HealthCopingViewModel::class.java -> HealthCopingViewModel(mApplication, mFormId) as T
            HealthAssistanceViewModel::class.java -> HealthAssistanceViewModel(mApplication, mFormId) as T
            HealthGapsViewModel::class.java -> HealthGapsViewModel(mApplication, mFormId) as T
            WashConditionsViewModel::class.java -> WashConditionsViewModel(mApplication, mFormId) as T
            WashCopingViewModel::class.java -> WashCopingViewModel(mApplication, mFormId) as T
            WashAssistanceViewModel::class.java -> WashAssistanceViewModel(mApplication, mFormId) as T
            WashGapsViewModel::class.java -> WashGapsViewModel(mApplication, mFormId) as T
            EvacuationInfoViewModel::class.java -> EvacuationInfoViewModel(mApplication, mFormId) as T
            SiteInfoViewModel::class.java -> SiteInfoViewModel(mApplication, mFormId) as T
            EvacuationAgeViewModel::class.java -> EvacuationAgeViewModel(mApplication, mFormId) as T
            EvacuationFacilitiesViewModel::class.java -> EvacuationFacilitiesViewModel(mApplication, mFormId) as T
            EvacuationWashViewModel::class.java -> EvacuationWashViewModel(mApplication, mFormId) as T
            EvacuationProtectionViewModel::class.java -> EvacuationProtectionViewModel(mApplication, mFormId) as T
            EvacuationCopingViewModel::class.java -> EvacuationCopingViewModel(mApplication, mFormId) as T
            else -> CaseStoriesViewModel(mApplication, mFormId) as T
        }
    }
}