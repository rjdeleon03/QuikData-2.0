package com.cpu.quikdata.di.module

import com.cpu.quikdata.base.BaseCreateFormFragment
import com.cpu.quikdata.di.module.viewmodel.CreateFormViewModelModule
import com.cpu.quikdata.di.module.viewmodel.HomeViewModelModule
import com.cpu.quikdata.di.module.viewmodel.NewFormsViewModelModule
import com.cpu.quikdata.feature.createform.basicselection.BasicSelectionFragment
import com.cpu.quikdata.feature.imageviewer.ImageViewerFragment
import com.cpu.quikdata.feature.main.home.HomeFragment
import com.cpu.quikdata.feature.main.newforms.NewFormsFragment
import dagger.Module
import dagger.Provides
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentBuilderModule {

    @ContributesAndroidInjector(
        modules = [HomeViewModelModule::class]
    )
    abstract fun contributeHomeFragment(): HomeFragment

    @ContributesAndroidInjector(
        modules = [CreateFormViewModelModule::class]
    )
    abstract fun contributeBaseCreateFormFragment(): BaseCreateFormFragment

    @ContributesAndroidInjector(
        modules = [CreateFormViewModelModule::class]
    )
    abstract fun contributeBasicSelectionFragment(): BasicSelectionFragment

    @ContributesAndroidInjector(
        modules = [NewFormsViewModelModule::class]
    )
    abstract fun contributeNewFormsFragment(): NewFormsFragment

    @ContributesAndroidInjector
    abstract fun contributeImageViewerFragment(): ImageViewerFragment
}