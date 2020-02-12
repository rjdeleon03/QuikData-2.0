package com.cpu.quikdata.di.module.viewmodel

import androidx.lifecycle.ViewModel
import com.cpu.quikdata.di.annotation.ViewModelKey
import com.cpu.quikdata.feature.createform.CreateFormViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class CreateFormViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(CreateFormViewModel::class)
    abstract fun bindsCreateFormViewModel(createFormViewModel: CreateFormViewModel): ViewModel

//    companion object {
//        @Provides
//        fun provideNewFormsRepository(database: AppDatabase,
//                                      firebaseHelper: FirebaseHelper
//        )
//                : NewFormsRepository
//                = NewFormsRepository(database, firebaseHelper)
//    }
}