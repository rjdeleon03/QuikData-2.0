package com.cpu.quikdata.di.module.createform.sections

import android.app.Application
import androidx.lifecycle.ViewModel
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
        fun provideBaselineDataRepository(application: Application, formId: String)
                : BaselineDataRepository {
            return BaselineDataRepository(application, formId)
        }
    }
}