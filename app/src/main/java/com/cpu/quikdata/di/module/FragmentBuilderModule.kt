package com.cpu.quikdata.di.module

import com.cpu.quikdata.feature.imageviewer.ImageViewerFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
 * Builds independent fragments
 */
@Module
abstract class FragmentBuilderModule {

    @ContributesAndroidInjector
    abstract fun contributeImageViewerFragment(): ImageViewerFragment
}