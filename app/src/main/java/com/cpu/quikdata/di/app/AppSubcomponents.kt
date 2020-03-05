package com.cpu.quikdata.di.app

import com.cpu.quikdata.di.component.main.PrefilledInfoComponent
import dagger.Module

@Module(
    subcomponents = [PrefilledInfoComponent::class]
)
interface AppSubcomponents