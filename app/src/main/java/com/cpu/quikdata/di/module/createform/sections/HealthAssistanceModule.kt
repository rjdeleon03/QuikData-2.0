package com.cpu.quikdata.di.module.createform.sections

import android.app.Application
import androidx.lifecycle.ViewModel
import com.cpu.quikdata.di.annotation.ViewModelKey
import com.cpu.quikdata.feature.createform.healthinfo.healthassistance.HealthAssistanceRepository
import com.cpu.quikdata.feature.createform.healthinfo.healthassistance.HealthAssistanceViewModel
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap

@Module
abstract class HealthAssistanceModule {

    @Binds
    @IntoMap
    @ViewModelKey(HealthAssistanceViewModel::class)
    abstract fun bindsHealthAssistanceViewModel(healthAssistanceViewModel: HealthAssistanceViewModel): ViewModel

    companion object {

        @Provides
        fun provideHealthAssistanceRepository(application: Application, formId: String)
                : HealthAssistanceRepository {
            return HealthAssistanceRepository(application, formId)
        }
    }
}