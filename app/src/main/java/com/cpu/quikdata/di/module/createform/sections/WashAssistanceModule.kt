package com.cpu.quikdata.di.module.createform.sections

import androidx.lifecycle.ViewModel
import com.cpu.quikdata.data.AppDatabase
import com.cpu.quikdata.di.annotation.FormIdQualifier
import com.cpu.quikdata.di.annotation.ViewModelKey
import com.cpu.quikdata.feature.createform.watersanitationinfo.washassistance.WashAssistanceRepository
import com.cpu.quikdata.feature.createform.watersanitationinfo.washassistance.WashAssistanceViewModel
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap

@Module
abstract class WashAssistanceModule {

    @Binds
    @IntoMap
    @ViewModelKey(WashAssistanceViewModel::class)
    abstract fun bindsWashAssistanceViewModel(washAssistanceViewModel: WashAssistanceViewModel): ViewModel

    companion object {

        @Provides
        fun provideWashAssistanceRepository(database: AppDatabase, @FormIdQualifier formId: String)
                : WashAssistanceRepository {
            return WashAssistanceRepository(database, formId)
        }
    }
}