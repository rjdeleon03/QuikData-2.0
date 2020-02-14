package com.cpu.quikdata.di.module

import com.cpu.quikdata.di.module.viewmodel.HomeViewModelModule
import com.cpu.quikdata.di.module.viewmodel.NewFormsViewModelModule
import com.cpu.quikdata.di.module.viewmodel.PrefilledInfoViewModelModule
import com.cpu.quikdata.feature.imageviewer.ImageViewerFragment
import com.cpu.quikdata.feature.main.home.HomeFragment
import com.cpu.quikdata.feature.main.newforms.NewFormsFragment
import com.cpu.quikdata.feature.main.prefilledinfo.PrefilledInfoFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
 * Builds independent fragments
 */
@Module
abstract class FragmentBuilderModule {

    @ContributesAndroidInjector(
        modules = [HomeViewModelModule::class]
    )
    abstract fun contributeHomeFragment(): HomeFragment

    @ContributesAndroidInjector(
        modules = [PrefilledInfoViewModelModule::class]
    )
    abstract fun contributePrefilledInfoFragment(): PrefilledInfoFragment

    @ContributesAndroidInjector
    abstract fun contributeImageViewerFragment(): ImageViewerFragment
}