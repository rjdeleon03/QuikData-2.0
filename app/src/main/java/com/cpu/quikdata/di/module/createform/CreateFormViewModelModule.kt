package com.cpu.quikdata.di.module.createform

import android.app.Application
import androidx.lifecycle.ViewModel
import com.cpu.quikdata.di.annotation.CreateFormActivityScope
import com.cpu.quikdata.di.annotation.ViewModelKey
import com.cpu.quikdata.feature.createform.CreateFormAltViewModel
import com.cpu.quikdata.feature.createform.CreateFormRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap

@Module
abstract class CreateFormViewModelModule {

    @Binds
    @IntoMap
    @CreateFormActivityScope
    @ViewModelKey(CreateFormAltViewModel::class)
    abstract fun bindsCreateFormViewModel(createFormViewModel: CreateFormAltViewModel): ViewModel

    companion object {

        @Provides
        @CreateFormActivityScope
        fun provideCreateFormRepository(application: Application, formId: String)
                : CreateFormRepository {
            return CreateFormRepository(application, formId)
        }
    }

}