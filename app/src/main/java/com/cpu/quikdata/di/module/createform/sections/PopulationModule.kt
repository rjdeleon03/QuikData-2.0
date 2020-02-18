package com.cpu.quikdata.di.module.createform.sections

import androidx.lifecycle.ViewModel
import com.cpu.quikdata.data.AppDatabase
import com.cpu.quikdata.di.annotation.FormIdQualifier
import com.cpu.quikdata.di.annotation.ViewModelKey
import com.cpu.quikdata.feature.createform.generalinfo.population.PopulationRepository
import com.cpu.quikdata.feature.createform.generalinfo.population.PopulationViewModel
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap

@Module
abstract class PopulationModule {

    @Binds
    @IntoMap
    @ViewModelKey(PopulationViewModel::class)
    abstract fun bindsPopulationViewModel(populationViewModel: PopulationViewModel): ViewModel

    companion object {

        @Provides
        fun providePopulationRepository(database: AppDatabase, @FormIdQualifier formId: String)
                : PopulationRepository {
            return PopulationRepository(database, formId)
        }
    }
}