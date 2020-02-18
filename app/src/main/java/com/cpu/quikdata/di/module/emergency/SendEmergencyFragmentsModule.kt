package com.cpu.quikdata.di.module.emergency

import com.cpu.quikdata.di.module.emergency.sections.CreateAlertModule
import com.cpu.quikdata.feature.emergency.createalert.CreateAlertFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class SendEmergencyFragmentsModule {

    @ContributesAndroidInjector(modules = [CreateAlertModule::class])
    abstract fun contributeCreateAlertFragment(): CreateAlertFragment
}