package com.cpu.quikdata.di.main.newforms

import androidx.lifecycle.ViewModel
import com.cpu.quikdata.di.app.module.ViewModelKey
import com.cpu.quikdata.feature.main.home.HomeViewModel
import com.cpu.quikdata.feature.main.newforms.NewFormsViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class NewFormsModule {

    @Binds
    @IntoMap
    @ViewModelKey(NewFormsViewModel::class)
    abstract fun bindNewFormsViewModel(newFormsViewModel: NewFormsViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(HomeViewModel::class)
    abstract fun bindHomeViewModel(homeViewModel: HomeViewModel): ViewModel
}