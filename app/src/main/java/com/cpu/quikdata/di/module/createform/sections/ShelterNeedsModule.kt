package com.cpu.quikdata.di.module.createform.sections

import android.app.Application
import androidx.lifecycle.ViewModel
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
        fun provideShelterNeedsRepository(application: Application, formId: String)
                : ShelterNeedsRepository {
            return ShelterNeedsRepository(application, formId)
        }
    }
}