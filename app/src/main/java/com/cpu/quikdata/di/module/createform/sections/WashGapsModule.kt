package com.cpu.quikdata.di.module.createform.sections

import android.app.Application
import androidx.lifecycle.ViewModel
import com.cpu.quikdata.di.annotation.FormIdQualifier
import com.cpu.quikdata.di.annotation.ViewModelKey
import com.cpu.quikdata.feature.createform.watersanitationinfo.washgaps.WashGapsRepository
import com.cpu.quikdata.feature.createform.watersanitationinfo.washgaps.WashGapsViewModel
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap

@Module
abstract class WashGapsModule {

    @Binds
    @IntoMap
    @ViewModelKey(WashGapsViewModel::class)
    abstract fun bindsWashGapsViewModel(washGapsViewModel: WashGapsViewModel): ViewModel

    companion object {

        @Provides
        fun provideWashGapsRepository(application: Application, @FormIdQualifier formId: String)
                : WashGapsRepository {
            return WashGapsRepository(application, formId)
        }
    }
}