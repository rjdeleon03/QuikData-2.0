package com.cpu.quikdata.di.module.createform.sections

import android.app.Application
import androidx.lifecycle.ViewModel
import com.cpu.quikdata.di.annotation.ViewModelKey
import com.cpu.quikdata.feature.createform.generalinfo.infrastructuredamage.InfrastructureDamageRepository
import com.cpu.quikdata.feature.createform.generalinfo.infrastructuredamage.InfrastructureDamageViewModel
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap

@Module
abstract class InfrastructureDamageModule {

    @Binds
    @IntoMap
    @ViewModelKey(InfrastructureDamageViewModel::class)
    abstract fun bindsInfrastructureDamageViewModel(infrastructureDamageViewModel: InfrastructureDamageViewModel): ViewModel

    companion object {

        @Provides
        fun provideInfrastructureDamageRepository(application: Application, formId: String)
                : InfrastructureDamageRepository {
            return InfrastructureDamageRepository(application, formId)
        }
    }
}