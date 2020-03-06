package com.cpu.quikdata.di.createform.activity

import com.cpu.quikdata.di.createform.formdetailsandbaseline.FormDetailsAndBaselineComponent
import com.cpu.quikdata.di.createform.generalinfo.GeneralInfoComponent
import dagger.Module

@Module(
    subcomponents = [
        FormDetailsAndBaselineComponent::class,
        GeneralInfoComponent::class
    ]
)
interface CreateFormSubcomponents