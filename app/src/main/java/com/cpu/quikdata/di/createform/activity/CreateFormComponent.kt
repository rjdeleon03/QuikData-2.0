package com.cpu.quikdata.di.createform.activity

import com.cpu.quikdata.base.BaseCreateFormFragment
import com.cpu.quikdata.di.ActivityScope
import com.cpu.quikdata.di.app.module.AssistedInjectModule
import com.cpu.quikdata.di.createform.casestories.CaseStoriesComponent
import com.cpu.quikdata.di.createform.evacuationinfo.EvacuationInfoComponent
import com.cpu.quikdata.di.createform.foodsecurityinfo.FoodSecurityInfoComponent
import com.cpu.quikdata.di.createform.formdetailsandbaseline.FormDetailsAndBaselineComponent
import com.cpu.quikdata.di.createform.generalinfo.GeneralInfoComponent
import com.cpu.quikdata.di.createform.healthinfo.HealthInfoComponent
import com.cpu.quikdata.di.createform.livelihoodsinfo.LivelihoodsInfoComponent
import com.cpu.quikdata.di.createform.shelterinfo.ShelterInfoComponent
import com.cpu.quikdata.di.createform.watersanitationinfo.WaterSanitationInfoComponent
import com.cpu.quikdata.feature.createform.activity.CreateFormActivity
import dagger.BindsInstance
import dagger.Subcomponent

@ActivityScope
@Subcomponent(
    modules = [
        AssistedInjectModule::class,
        CreateFormModule::class,
        CreateFormSubcomponents::class
    ]
)
interface CreateFormComponent {

    @Subcomponent.Factory
    interface Factory {
        fun create(@BindsInstance createFormActivity: CreateFormActivity): CreateFormComponent
    }

    fun formDetailsAndBaselineComponent(): FormDetailsAndBaselineComponent.Factory
    fun generalInfoComponent(): GeneralInfoComponent.Factory
    fun shelterInfoComponent(): ShelterInfoComponent.Factory
    fun foodSecurityInfoComponent(): FoodSecurityInfoComponent.Factory
    fun livelihoodsInfoComponent(): LivelihoodsInfoComponent.Factory
    fun healthInfoComponent(): HealthInfoComponent.Factory
    fun waterSanitationInfoComponent(): WaterSanitationInfoComponent.Factory
    fun evacuationInfoComponent(): EvacuationInfoComponent.Factory
    fun caseStoriesComponent(): CaseStoriesComponent.Factory

    fun inject(createFormActivity: CreateFormActivity)
    fun inject(createFormFragment: BaseCreateFormFragment)
}