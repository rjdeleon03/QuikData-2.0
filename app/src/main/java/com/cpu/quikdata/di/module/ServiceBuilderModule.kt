package com.cpu.quikdata.di.module

import com.cpu.quikdata.feature.service.QuikDataMessagingService
import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
 * Builds all activities
 */
@Module
abstract class ServiceBuilderModule {

    @ContributesAndroidInjector
    abstract fun contributeMessagingService(): QuikDataMessagingService
}