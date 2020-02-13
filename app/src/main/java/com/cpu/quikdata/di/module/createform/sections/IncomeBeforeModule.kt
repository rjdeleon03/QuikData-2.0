package com.cpu.quikdata.di.module.createform.sections

import androidx.lifecycle.ViewModel
import com.cpu.quikdata.data.AppDatabase
import com.cpu.quikdata.di.annotation.FormIdQualifier
import com.cpu.quikdata.di.annotation.ViewModelKey
import com.cpu.quikdata.feature.createform.livelihoodsinfo.incomebefore.IncomeBeforeRepository
import com.cpu.quikdata.feature.createform.livelihoodsinfo.incomebefore.IncomeBeforeViewModel
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap

@Module
abstract class IncomeBeforeModule {

    @Binds
    @IntoMap
    @ViewModelKey(IncomeBeforeViewModel::class)
    abstract fun bindsIncomeBeforeViewModel(incomeBeforeViewModel: IncomeBeforeViewModel): ViewModel

    companion object {

        @Provides
        fun provideIncomeBeforeRepository(database: AppDatabase, @FormIdQualifier formId: String)
                : IncomeBeforeRepository {
            return IncomeBeforeRepository(database, formId)
        }
    }
}