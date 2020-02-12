package com.cpu.quikdata.di.module.createform.sections

import android.app.Application
import androidx.lifecycle.ViewModel
import com.cpu.quikdata.di.annotation.ViewModelKey
import com.cpu.quikdata.feature.createform.livelihoodsinfo.estimateddamage.EstimatedDamageRepository
import com.cpu.quikdata.feature.createform.livelihoodsinfo.estimateddamage.EstimatedDamageViewModel
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap

@Module
abstract class EstimatedDamageModule {

    @Binds
    @IntoMap
    @ViewModelKey(EstimatedDamageViewModel::class)
    abstract fun bindsEstimatedDamageViewModel(estimatedDamageViewModel: EstimatedDamageViewModel): ViewModel

    companion object {

        @Provides
        fun provideEstimatedDamageRepository(application: Application, formId: String)
                : EstimatedDamageRepository {
            return EstimatedDamageRepository(application, formId)
        }
    }
}