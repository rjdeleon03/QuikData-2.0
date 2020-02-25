package com.cpu.quikdata.di.module

import com.cpu.quikdata.di.annotation.CreateFormActivityScope
import com.cpu.quikdata.di.annotation.SendEmergencyActivityScope
import com.cpu.quikdata.di.module.createform.CreateFormFragmentsModule
import com.cpu.quikdata.di.module.createform.CreateFormSharedModule
import com.cpu.quikdata.di.module.emergency.SendEmergencyFragmentsModule
import com.cpu.quikdata.di.module.emergency.SendEmergencySharedModule
import com.cpu.quikdata.di.module.imageviewer.ImageViewerModule
import com.cpu.quikdata.di.module.main.MainActivityFragmentsModule
import com.cpu.quikdata.di.module.main.MainActivitySharedModule
import com.cpu.quikdata.feature.consortium.ConsortiumActivity
import com.cpu.quikdata.feature.createform.CreateFormActivity
import com.cpu.quikdata.feature.emergency.SendEmergencyActivity
import com.cpu.quikdata.feature.main.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
 * Builds all activities
 */
@Module(includes = [ImageViewerModule::class])
abstract class ActivityBuilderModule {

    @CreateFormActivityScope
    @ContributesAndroidInjector(
        modules = [MainActivitySharedModule::class, MainActivityFragmentsModule::class]
    )
    abstract fun contributeMainActivity(): MainActivity

    @CreateFormActivityScope
    @ContributesAndroidInjector(
        modules = [CreateFormSharedModule::class, CreateFormFragmentsModule::class]
    )
    abstract fun contributeCreateFormActivity(): CreateFormActivity

    @CreateFormActivityScope
    @ContributesAndroidInjector
    abstract fun contributeConsortiumActivity(): ConsortiumActivity

    @SendEmergencyActivityScope
    @ContributesAndroidInjector(
        modules = [SendEmergencySharedModule::class, SendEmergencyFragmentsModule::class]
    )
    abstract fun contributeSendEmergencyActivity(): SendEmergencyActivity
}