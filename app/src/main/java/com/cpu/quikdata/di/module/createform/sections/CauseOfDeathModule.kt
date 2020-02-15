package com.cpu.quikdata.di.module.createform.sections

import androidx.lifecycle.ViewModel
import com.cpu.quikdata.data.AppDatabase
import com.cpu.quikdata.di.annotation.FormIdQualifier
import com.cpu.quikdata.di.annotation.ViewModelKey
import com.cpu.quikdata.feature.createform.generalinfo.causeofdeath.CauseOfDeathRepository
import com.cpu.quikdata.feature.createform.generalinfo.causeofdeath.CauseOfDeathViewModel
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap

@Module
abstract class CauseOfDeathModule {

    @Binds
    @IntoMap
    @ViewModelKey(CauseOfDeathViewModel::class)
    abstract fun bindsCauseOfDeathViewModel(causeOfDeathViewModel: CauseOfDeathViewModel): ViewModel

    companion object {

        @Provides
        fun provideCauseOfDeathRepository(database: AppDatabase, @FormIdQualifier formId: String)
                : CauseOfDeathRepository {
            return CauseOfDeathRepository(database, formId)
        }
    }
}