package com.cpu.quikdata.di.module.createform.sections

import android.app.Application
import androidx.lifecycle.ViewModel
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
        fun provideCasualtiesRepository(application: Application, formId: String)
                : CasualtiesRepository {
            return CasualtiesRepository(application, formId)
        }
    }
}