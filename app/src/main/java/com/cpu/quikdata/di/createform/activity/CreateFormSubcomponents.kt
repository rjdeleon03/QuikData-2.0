package com.cpu.quikdata.di.createform.activity

import com.cpu.quikdata.di.createform.formdetailsandbaseline.FormDetailsAndBaselineComponent
import com.cpu.quikdata.di.createform.generalinfo.GeneralInfoComponent
import com.cpu.quikdata.di.createform.shelterinfo.ShelterInfoComponent
import dagger.Module

@Module(
    subcomponents = [
        FormDetailsAndBaselineComponent::class,
        GeneralInfoComponent::class,
        ShelterInfoComponent::class
    ]
)
interface CreateFormSubcomponents