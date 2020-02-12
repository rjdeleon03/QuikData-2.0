package com.cpu.quikdata.di.module.createform.sections

import android.app.Application
import androidx.lifecycle.ViewModel
import com.cpu.quikdata.di.annotation.ViewModelKey
import com.cpu.quikdata.feature.createform.livelihoodsinfo.incomeafter.IncomeAfterRepository
import com.cpu.quikdata.feature.createform.livelihoodsinfo.incomeafter.IncomeAfterViewModel
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap

@Module
abstract class IncomeAfterModule {

    @Binds
    @IntoMap
    @ViewModelKey(IncomeAfterViewModel::class)
    abstract fun bindsIncomeAfterViewModel(incomeAfterViewModel: IncomeAfterViewModel): ViewModel

    companion object {

        @Provides
        fun provideIncomeAfterRepository(application: Application, formId: String)
                : IncomeAfterRepository {
            return IncomeAfterRepository(application, formId)
        }
    }
}