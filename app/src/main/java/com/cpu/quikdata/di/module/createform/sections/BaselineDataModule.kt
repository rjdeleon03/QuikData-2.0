package com.cpu.quikdata.di.module.createform.sections

import androidx.lifecycle.ViewModel
import com.cpu.quikdata.data.AppDatabase
import com.cpu.quikdata.di.annotation.FormIdQualifier
import com.cpu.quikdata.di.annotation.ViewModelKey
import com.cpu.quikdata.feature.createform.formdetailsandbaseline.baselinedata.BaselineDataRepository
import com.cpu.quikdata.feature.createform.formdetailsandbaseline.baselinedata.BaselineDataViewModel
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap

@Module
abstract class BaselineDataModule {

    @Binds
    @IntoMap
    @ViewModelKey(BaselineDataViewModel::class)
    abstract fun bindsBaselineDataViewModel(baselineDataViewModel: BaselineDataViewModel): ViewModel

    companion object {

        @Provides
        fun provideBaselineDataRepository(database: AppDatabase, @FormIdQualifier formId: String)
                : BaselineDataRepository {
            return BaselineDataRepository(database, formId)
        }
    }
}