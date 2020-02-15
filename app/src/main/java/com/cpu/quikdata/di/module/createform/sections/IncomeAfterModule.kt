package com.cpu.quikdata.di.module.createform.sections

import androidx.lifecycle.ViewModel
import com.cpu.quikdata.data.AppDatabase
import com.cpu.quikdata.di.annotation.FormIdQualifier
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
        fun provideIncomeAfterRepository(database: AppDatabase, @FormIdQualifier formId: String)
                : IncomeAfterRepository {
            return IncomeAfterRepository(database, formId)
        }
    }
}