package com.cpu.quikdata.di.module

import com.cpu.quikdata.feature.receiver.NetworkChangeReceiver
import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
 * Builds all activities
 */
@Module
abstract class BroadcastReceiverBuilderModule {

    @ContributesAndroidInjector
    abstract fun contributeNetworkChangeReceiver(): NetworkChangeReceiver
}