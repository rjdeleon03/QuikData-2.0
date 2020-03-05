package com.cpu.quikdata.di.createform.activity

import com.cpu.quikdata.di.createform.activity.CreateFormComponent
import com.cpu.quikdata.di.createform.formdetailsandbaseline.FormDetailsAndBaselineComponent
import com.cpu.quikdata.di.main.newforms.NewFormsComponent
import com.cpu.quikdata.di.main.prefilledinfo.PrefilledInfoComponent
import dagger.Module

@Module(
    subcomponents = [
        FormDetailsAndBaselineComponent::class
    ]
)
interface CreateFormSubcomponents