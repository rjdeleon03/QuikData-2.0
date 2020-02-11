package com.cpu.quikdata.di.newforms

import androidx.lifecycle.ViewModel
import com.cpu.quikdata.di.annotation.ViewModelKey
import com.cpu.quikdata.feature.main.newforms.NewFormsViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class NewFormsViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(NewFormsViewModel::class)
    abstract fun bindNewFormsViewModel(viewModel: NewFormsViewModel): ViewModel
}