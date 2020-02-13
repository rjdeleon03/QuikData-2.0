package com.cpu.quikdata.di.module.createform.sections

import android.app.Application
import androidx.lifecycle.ViewModel
import com.cpu.quikdata.di.annotation.FormIdQualifier
import com.cpu.quikdata.di.annotation.ViewModelKey
import com.cpu.quikdata.feature.createform.shelterinfo.sheltercoping.ShelterCopingRepository
import com.cpu.quikdata.feature.createform.shelterinfo.sheltercoping.ShelterCopingViewModel
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap

@Module
abstract class ShelterCopingModule {

    @Binds
    @IntoMap
    @ViewModelKey(ShelterCopingViewModel::class)
    abstract fun bindsShelterCopingViewModel(shelterCopingViewModel: ShelterCopingViewModel): ViewModel

    companion object {

        @Provides
        fun provideShelterCopingRepository(application: Application, @FormIdQualifier formId: String)
                : ShelterCopingRepository {
            return ShelterCopingRepository(application, formId)
        }
    }
}