package com.cpu.quikdata.di.module.createform

import com.cpu.quikdata.base.BaseCreateFormFragment
import com.cpu.quikdata.feature.createform.basicselection.BasicSelectionFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
 * Supports injection for child fragments of CreateFormActivity
 */
@Module
abstract class CreateFormFragmentsModule {

    @ContributesAndroidInjector
    abstract fun contributeBaseCreateFormFragment(): BaseCreateFormFragment

    @ContributesAndroidInjector
    abstract fun contributeBasicSelectionFragment(): BasicSelectionFragment
}