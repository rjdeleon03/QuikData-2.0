package com.cpu.quikdata.di.module.createform.sections

import androidx.lifecycle.ViewModel
import com.cpu.quikdata.data.AppDatabase
import com.cpu.quikdata.di.annotation.FormIdQualifier
import com.cpu.quikdata.di.annotation.ViewModelKey
import com.cpu.quikdata.feature.createform.healthinfo.diseases.DiseasesRepository
import com.cpu.quikdata.feature.createform.healthinfo.diseases.DiseasesViewModel
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap

@Module
abstract class DiseasesModule {

    @Binds
    @IntoMap
    @ViewModelKey(DiseasesViewModel::class)
    abstract fun bindsDiseasesViewModel(diseasesViewModel: DiseasesViewModel): ViewModel

    companion object {

        @Provides
        fun provideDiseasesRepository(database: AppDatabase, @FormIdQualifier formId: String)
                : DiseasesRepository {
            return DiseasesRepository(database, formId)
        }
    }
}