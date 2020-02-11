package com.cpu.quikdata.di.module.viewmodel

import androidx.lifecycle.ViewModel
import com.cpu.quikdata.common.FirebaseHelper
import com.cpu.quikdata.data.AppDatabase
import com.cpu.quikdata.di.annotation.ViewModelKey
import com.cpu.quikdata.feature.main.newforms.NewFormsRepository
import com.cpu.quikdata.feature.main.newforms.NewFormsViewModel
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap

@Module
abstract class NewFormsViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(NewFormsViewModel::class)
    abstract fun bindNewFormsViewModel(viewModel: NewFormsViewModel): ViewModel

    companion object {
        @Provides
        fun provideNewFormsRepository(database: AppDatabase,
                                      firebaseHelper: FirebaseHelper)
                : NewFormsRepository
                = NewFormsRepository(database, firebaseHelper)
    }
}