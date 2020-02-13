package com.cpu.quikdata.di.module.createform.sections

import androidx.lifecycle.ViewModel
import com.cpu.quikdata.data.AppDatabase
import com.cpu.quikdata.di.annotation.FormIdQualifier
import com.cpu.quikdata.di.annotation.ViewModelKey
import com.cpu.quikdata.feature.createform.healthinfo.healthcoping.HealthCopingRepository
import com.cpu.quikdata.feature.createform.healthinfo.healthcoping.HealthCopingViewModel
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap

@Module
abstract class HealthCopingModule {

    @Binds
    @IntoMap
    @ViewModelKey(HealthCopingViewModel::class)
    abstract fun bindsHealthCopingViewModel(healthCopingViewModel: HealthCopingViewModel): ViewModel

    companion object {

        @Provides
        fun provideHealthCopingRepository(database: AppDatabase, @FormIdQualifier formId: String)
                : HealthCopingRepository {
            return HealthCopingRepository(database, formId)
        }
    }
}