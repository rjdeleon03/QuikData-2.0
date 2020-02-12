package com.cpu.quikdata.di.module.createform.sections

import android.app.Application
import androidx.lifecycle.ViewModel
import com.cpu.quikdata.di.annotation.ViewModelKey
import com.cpu.quikdata.feature.createform.shelterinfo.shelterassistance.ShelterAssistanceRepository
import com.cpu.quikdata.feature.createform.shelterinfo.shelterassistance.ShelterAssistanceViewModel
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap

@Module
abstract class ShelterAssistanceModule {

    @Binds
    @IntoMap
    @ViewModelKey(ShelterAssistanceViewModel::class)
    abstract fun bindsShelterAssistanceViewModel(shelterAssistanceViewModel: ShelterAssistanceViewModel): ViewModel

    companion object {

        @Provides
        fun provideShelterAssistanceRepository(application: Application, formId: String)
                : ShelterAssistanceRepository {
            return ShelterAssistanceRepository(application, formId)
        }
    }
}