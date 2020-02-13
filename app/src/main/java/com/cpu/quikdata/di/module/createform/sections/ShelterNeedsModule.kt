package com.cpu.quikdata.di.module.createform.sections

import androidx.lifecycle.ViewModel
import com.cpu.quikdata.data.AppDatabase
import com.cpu.quikdata.di.annotation.FormIdQualifier
import com.cpu.quikdata.di.annotation.ViewModelKey
import com.cpu.quikdata.feature.createform.shelterinfo.shelterneeds.ShelterNeedsRepository
import com.cpu.quikdata.feature.createform.shelterinfo.shelterneeds.ShelterNeedsViewModel
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap

@Module
abstract class ShelterNeedsModule {

    @Binds
    @IntoMap
    @ViewModelKey(ShelterNeedsViewModel::class)
    abstract fun bindsShelterNeedsViewModel(shelterNeedsViewModel: ShelterNeedsViewModel): ViewModel

    companion object {

        @Provides
        fun provideShelterNeedsRepository(database: AppDatabase, @FormIdQualifier formId: String)
                : ShelterNeedsRepository {
            return ShelterNeedsRepository(database, formId)
        }
    }
}