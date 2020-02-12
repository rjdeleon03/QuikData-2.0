package com.cpu.quikdata.di.module.createform.sections

import android.app.Application
import androidx.lifecycle.ViewModel
import com.cpu.quikdata.di.annotation.ViewModelKey
import com.cpu.quikdata.feature.createform.shelterinfo.housedamage.HouseDamageRepository
import com.cpu.quikdata.feature.createform.shelterinfo.housedamage.HouseDamageViewModel
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap

@Module
abstract class HouseDamageModule {

    @Binds
    @IntoMap
    @ViewModelKey(HouseDamageViewModel::class)
    abstract fun bindsHouseDamageViewModel(houseDamageViewModel: HouseDamageViewModel): ViewModel

    companion object {

        @Provides
        fun provideHouseDamageRepository(application: Application, formId: String)
                : HouseDamageRepository {
            return HouseDamageRepository(application, formId)
        }
    }
}