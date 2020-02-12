package com.cpu.quikdata.di.module.viewmodel

import android.app.Application
import androidx.lifecycle.ViewModel
import com.cpu.quikdata.common.FirebaseHelper
import com.cpu.quikdata.data.AppDatabase
import com.cpu.quikdata.di.annotation.CreateFormActivityScope
import com.cpu.quikdata.di.annotation.ViewModelKey
import com.cpu.quikdata.feature.createform.CreateFormRepository
import com.cpu.quikdata.feature.createform.CreateFormViewModel
import com.cpu.quikdata.feature.main.home.HomeViewModel
import com.cpu.quikdata.feature.main.newforms.NewFormsRepository
import com.squareup.inject.assisted.dagger2.AssistedModule
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap

@AssistedModule
@Module(includes = [AssistedInject_CreateFormViewModelModule::class])
abstract class CreateFormViewModelModule {

    @Binds
    @IntoMap
    @CreateFormActivityScope
    @ViewModelKey(CreateFormViewModel::class)
    abstract fun bindsCreateFormViewModel(createFormViewModel: CreateFormViewModel): ViewModel

    companion object {

        @Provides
        @CreateFormActivityScope
        fun provideCreateFormRepository(application: Application, formId: String)
                : CreateFormRepository {
            return CreateFormRepository(application, formId)
        }
    }
}