package com.cpu.quikdata.di.module.imageviewer

import com.cpu.quikdata.feature.imageviewer.ImageViewerFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ImageViewerModule {

    @ContributesAndroidInjector
    abstract fun contributeImageViewerFragment(): ImageViewerFragment
}