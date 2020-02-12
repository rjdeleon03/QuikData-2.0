package com.cpu.quikdata.di.module.createform.sections

import android.app.Application
import androidx.lifecycle.ViewModel
import com.cpu.quikdata.di.annotation.ViewModelKey
import com.cpu.quikdata.feature.createform.watersanitationinfo.washcoping.WashCopingRepository
import com.cpu.quikdata.feature.createform.watersanitationinfo.washcoping.WashCopingViewModel
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap

@Module
abstract class WashCopingModule {

    @Binds
    @IntoMap
    @ViewModelKey(WashCopingViewModel::class)
    abstract fun bindsWashCopingViewModel(washCopingViewModel: WashCopingViewModel): ViewModel

    companion object {

        @Provides
        fun provideWashCopingRepository(application: Application, formId: String)
                : WashCopingRepository {
            return WashCopingRepository(application, formId)
        }
    }
}