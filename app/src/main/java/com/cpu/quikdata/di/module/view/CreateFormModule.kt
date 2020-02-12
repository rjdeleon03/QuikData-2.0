package com.cpu.quikdata.di.module.view

import com.cpu.quikdata.base.BaseCreateFormFragment
import com.cpu.quikdata.feature.createform.basicselection.BasicSelectionFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class CreateFormModule {

    @ContributesAndroidInjector
    abstract fun contributeBaseCreateFormFragment(): BaseCreateFormFragment

    @ContributesAndroidInjector
    abstract fun contributeBasicSelectionFragment(): BasicSelectionFragment
}