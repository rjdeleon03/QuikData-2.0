package com.cpu.quikdata.di.createform.activity

import com.cpu.quikdata.di.createform.foodsecurityinfo.FoodSecurityInfoComponent
import com.cpu.quikdata.di.createform.formdetailsandbaseline.FormDetailsAndBaselineComponent
import com.cpu.quikdata.di.createform.generalinfo.GeneralInfoComponent
import com.cpu.quikdata.di.createform.livelihoodsinfo.LivelihoodsInfoComponent
import com.cpu.quikdata.di.createform.shelterinfo.ShelterInfoComponent
import dagger.Module

@Module(
    subcomponents = [
        FormDetailsAndBaselineComponent::class,
        GeneralInfoComponent::class,
        ShelterInfoComponent::class,
        FoodSecurityInfoComponent::class,
        LivelihoodsInfoComponent::class
    ]
)
interface CreateFormSubcomponents