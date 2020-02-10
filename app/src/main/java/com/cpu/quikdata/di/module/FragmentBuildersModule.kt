package com.cpu.quikdata.di.module

import com.cpu.quikdata.feature.imageviewer.ImageViewerFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentBuildersModule {

    @ContributesAndroidInjector
    abstract fun contributeImageViewerFragment(): ImageViewerFragment
}