package com.cpu.quikdata.di.module.createform.sections

import androidx.lifecycle.ViewModel
import com.cpu.quikdata.data.AppDatabase
import com.cpu.quikdata.di.annotation.FormIdQualifier
import com.cpu.quikdata.di.annotation.ViewModelKey
import com.cpu.quikdata.feature.createform.healthinfo.healthgaps.HealthGapsRepository
import com.cpu.quikdata.feature.createform.healthinfo.healthgaps.HealthGapsViewModel
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap

@Module
abstract class HealthGapsModule {

    @Binds
    @IntoMap
    @ViewModelKey(HealthGapsViewModel::class)
    abstract fun bindsHealthGapsViewModel(healthGapsViewModel: HealthGapsViewModel): ViewModel

    companion object {

        @Provides
        fun provideHealthGapsRepository(database: AppDatabase, @FormIdQualifier formId: String)
                : HealthGapsRepository {
            return HealthGapsRepository(database, formId)
        }
    }
}