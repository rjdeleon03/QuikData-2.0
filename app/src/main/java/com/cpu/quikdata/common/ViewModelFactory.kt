package com.cpu.quikdata.common

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.cpu.quikdata.feature.createform.foodsecurityinfo.foodsecuritycoping.FoodSecurityCopingViewModel
import com.cpu.quikdata.feature.createform.foodsecurityinfo.foodsecuritygaps.FoodSecurityGapsViewModel
import com.cpu.quikdata.feature.createform.foodsecurityinfo.foodsecurityimpact.FoodSecurityImpactViewModel
import com.cpu.quikdata.feature.createform.foodsecurityinfo.foodsecurityneeds.FoodSecurityNeedsViewModel
import com.cpu.quikdata.feature.createform.formdetails.FormDetailsViewModel
import com.cpu.quikdata.feature.createform.generalinfo.calamityinfo.CalamityInfoViewModel
import com.cpu.quikdata.feature.createform.generalinfo.casualties.CasualtiesViewModel
import com.cpu.quikdata.feature.createform.generalinfo.causeofdeath.CauseOfDeathViewModel
import com.cpu.quikdata.feature.createform.generalinfo.families.FamiliesViewModel
import com.cpu.quikdata.feature.createform.generalinfo.infrastructuredamage.InfrastructureDamageViewModel
import com.cpu.quikdata.feature.createform.generalinfo.population.PopulationViewModel
import com.cpu.quikdata.feature.createform.generalinfo.vulnerable.VulnerableViewModel
import com.cpu.quikdata.feature.createform.healthinfo.diseases.DiseasesViewModel
import com.cpu.quikdata.feature.createform.healthinfo.healthcoping.HealthCopingViewModel
import com.cpu.quikdata.feature.createform.healthinfo.healthgaps.HealthGapsViewModel
import com.cpu.quikdata.feature.createform.healthinfo.psychosocial.PsychosocialViewModel
import com.cpu.quikdata.feature.createform.healthinfo.specialneeds.SpecialNeedsViewModel
import com.cpu.quikdata.feature.createform.livelihoodsinfo.livelihoodscoping.LivelihoodsCopingViewModel
import com.cpu.quikdata.feature.createform.livelihoodsinfo.livelihoodsgaps.LivelihoodsGapsViewModel
import com.cpu.quikdata.feature.createform.livelihoodsinfo.livelihoodsneeds.LivelihoodsNeedsViewModel
import com.cpu.quikdata.feature.createform.shelterinfo.housedamage.HouseDamageViewModel
import com.cpu.quikdata.feature.createform.shelterinfo.sheltercoping.ShelterCopingViewModel
import com.cpu.quikdata.feature.createform.shelterinfo.sheltergaps.ShelterGapsViewModel
import com.cpu.quikdata.feature.createform.shelterinfo.shelterneeds.ShelterNeedsViewModel
import com.cpu.quikdata.feature.createform.watersanitationinfo.washcoping.WashCopingViewModel

@Suppress("UNCHECKED_CAST")
class ViewModelFactory(application: Application, formId: String) :
    ViewModelProvider.AndroidViewModelFactory(application) {

    private val mApplication = application
    private val mFormId = formId

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {

        return when (modelClass) {
            CalamityInfoViewModel::class.java -> CalamityInfoViewModel(mApplication, mFormId) as T
            PopulationViewModel::class.java -> PopulationViewModel(mApplication, mFormId) as T
            FamiliesViewModel::class.java -> FamiliesViewModel(mApplication, mFormId) as T
            VulnerableViewModel::class.java -> VulnerableViewModel(mApplication, mFormId) as T
            CasualtiesViewModel::class.java -> CasualtiesViewModel(mApplication, mFormId) as T
            CauseOfDeathViewModel::class.java -> CauseOfDeathViewModel(mApplication, mFormId) as T
            InfrastructureDamageViewModel::class.java -> InfrastructureDamageViewModel(mApplication, mFormId) as T
            HouseDamageViewModel::class.java -> HouseDamageViewModel(mApplication, mFormId) as T
            ShelterCopingViewModel::class.java -> ShelterCopingViewModel(mApplication, mFormId) as T
            ShelterNeedsViewModel::class.java -> ShelterNeedsViewModel(mApplication, mFormId) as T
            ShelterGapsViewModel::class.java -> ShelterGapsViewModel(mApplication, mFormId) as T
            FoodSecurityImpactViewModel::class.java -> FoodSecurityImpactViewModel(mApplication, mFormId) as T
            FoodSecurityCopingViewModel::class.java -> FoodSecurityCopingViewModel(mApplication, mFormId) as T
            FoodSecurityNeedsViewModel::class.java -> FoodSecurityNeedsViewModel(mApplication, mFormId) as T
            FoodSecurityGapsViewModel::class.java -> FoodSecurityGapsViewModel(mApplication, mFormId) as T
            LivelihoodsCopingViewModel::class.java -> LivelihoodsCopingViewModel(mApplication, mFormId) as T
            LivelihoodsNeedsViewModel::class.java -> LivelihoodsNeedsViewModel(mApplication, mFormId) as T
            LivelihoodsGapsViewModel::class.java -> LivelihoodsGapsViewModel(mApplication, mFormId) as T
            DiseasesViewModel::class.java -> DiseasesViewModel(mApplication, mFormId) as T
            SpecialNeedsViewModel::class.java -> SpecialNeedsViewModel(mApplication, mFormId) as T
            PsychosocialViewModel::class.java -> PsychosocialViewModel(mApplication, mFormId) as T
            HealthCopingViewModel::class.java -> HealthCopingViewModel(mApplication, mFormId) as T
            HealthGapsViewModel::class.java -> HealthGapsViewModel(mApplication, mFormId) as T
            WashCopingViewModel::class.java -> WashCopingViewModel(mApplication, mFormId) as T
            else -> FormDetailsViewModel(mApplication, mFormId) as T
        }
    }
}