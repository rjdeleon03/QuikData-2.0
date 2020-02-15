package com.cpu.quikdata.di.module.createform.sections

import androidx.lifecycle.ViewModel
import com.cpu.quikdata.data.AppDatabase
import com.cpu.quikdata.di.annotation.FormIdQualifier
import com.cpu.quikdata.di.annotation.ViewModelKey
import com.cpu.quikdata.feature.createform.generalinfo.casualties.CasualtiesRepository
import com.cpu.quikdata.feature.createform.generalinfo.casualties.CasualtiesViewModel
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap

@Module
abstract class CasualtiesModule {

    @Binds
    @IntoMap
    @ViewModelKey(CasualtiesViewModel::class)
    abstract fun bindsCasualtiesViewModel(casualtiesViewModel: CasualtiesViewModel): ViewModel

    companion object {

        @Provides
        fun provideCasualtiesRepository(database: AppDatabase, @FormIdQualifier formId: String)
                : CasualtiesRepository {
            return CasualtiesRepository(database, formId)
        }
    }
}