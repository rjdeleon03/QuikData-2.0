package com.cpu.quikdata.di.module.createform.sections

import androidx.lifecycle.ViewModel
import com.cpu.quikdata.data.AppDatabase
import com.cpu.quikdata.di.annotation.FormIdQualifier
import com.cpu.quikdata.di.annotation.ViewModelKey
import com.cpu.quikdata.feature.createform.watersanitationinfo.washconditions.WashConditionsRepository
import com.cpu.quikdata.feature.createform.watersanitationinfo.washconditions.WashConditionsViewModel
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap

@Module
abstract class WashConditionsModule {

    @Binds
    @IntoMap
    @ViewModelKey(WashConditionsViewModel::class)
    abstract fun bindsWashConditionsViewModel(washConditionsViewModel: WashConditionsViewModel): ViewModel

    companion object {

        @Provides
        fun provideWashConditionsRepository(database: AppDatabase, @FormIdQualifier formId: String)
                : WashConditionsRepository {
            return WashConditionsRepository(database, formId)
        }
    }
}