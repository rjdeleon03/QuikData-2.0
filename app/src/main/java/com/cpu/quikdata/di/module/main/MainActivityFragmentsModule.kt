package com.cpu.quikdata.di.module.main

import com.cpu.quikdata.di.module.main.sections.HomeModule
import com.cpu.quikdata.di.module.main.sections.NewFormsModule
import com.cpu.quikdata.di.module.main.sections.PrefilledInfoModule
import com.cpu.quikdata.feature.main.about.AboutFragment
import com.cpu.quikdata.feature.main.home.HomeFragment
import com.cpu.quikdata.feature.main.newforms.NewFormsFragment
import com.cpu.quikdata.feature.main.prefilledinfo.PrefilledInfoFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class MainActivityFragmentsModule {

    @ContributesAndroidInjector(
        modules = [NewFormsModule::class]
    )
    abstract fun contributeNewFormsFragment(): NewFormsFragment

    @ContributesAndroidInjector(
        modules = [HomeModule::class]
    )
    abstract fun contributeHomeFragment(): HomeFragment

    @ContributesAndroidInjector(
        modules = [PrefilledInfoModule::class]
    )
    abstract fun contributePrefilledInfoFragment(): PrefilledInfoFragment

    @ContributesAndroidInjector
    abstract fun contributeAboutFragment(): AboutFragment
}