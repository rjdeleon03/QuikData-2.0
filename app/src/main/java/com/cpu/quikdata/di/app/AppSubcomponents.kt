package com.cpu.quikdata.di.app

import com.cpu.quikdata.di.createform.activity.CreateFormComponent
import com.cpu.quikdata.di.main.newforms.NewFormsComponent
import com.cpu.quikdata.di.main.prefilledinfo.PrefilledInfoComponent
import dagger.Module

@Module(
    subcomponents = [
        PrefilledInfoComponent::class,
        NewFormsComponent::class,
        CreateFormComponent::class
    ]
)
interface AppSubcomponents