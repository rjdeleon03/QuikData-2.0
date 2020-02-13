package com.cpu.quikdata.di.module.createform.sections

import android.app.Application
import androidx.lifecycle.ViewModel
import com.cpu.quikdata.di.annotation.FormIdQualifier
import com.cpu.quikdata.di.annotation.ViewModelKey
import com.cpu.quikdata.feature.createform.shelterinfo.sheltergaps.ShelterGapsRepository
import com.cpu.quikdata.feature.createform.shelterinfo.sheltergaps.ShelterGapsViewModel
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap

@Module
abstract class ShelterGapsModule {

    @Binds
    @IntoMap
    @ViewModelKey(ShelterGapsViewModel::class)
    abstract fun bindsShelterGapsViewModel(shelterGapsViewModel: ShelterGapsViewModel): ViewModel

    companion object {

        @Provides
        fun provideShelterGapsRepository(application: Application, @FormIdQualifier formId: String)
                : ShelterGapsRepository {
            return ShelterGapsRepository(application, formId)
        }
    }
}