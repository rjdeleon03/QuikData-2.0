package com.cpu.quikdata.di.module

import com.cpu.quikdata.di.newforms.NewFormsViewModelModule
import com.cpu.quikdata.feature.imageviewer.ImageViewerFragment
import com.cpu.quikdata.feature.main.newforms.NewFormsFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentBuilderModule {

    @ContributesAndroidInjector(
        modules = [NewFormsViewModelModule::class]
    )
    abstract fun contributeNewFormsFragment(): NewFormsFragment

    @ContributesAndroidInjector
    abstract fun contributeImageViewerFragment(): ImageViewerFragment
}