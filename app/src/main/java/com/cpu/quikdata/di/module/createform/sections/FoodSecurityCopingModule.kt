package com.cpu.quikdata.di.module.createform.sections

import android.app.Application
import androidx.lifecycle.ViewModel
import com.cpu.quikdata.di.annotation.ViewModelKey
import com.cpu.quikdata.feature.createform.foodsecurityinfo.foodsecuritycoping.FoodSecurityCopingRepository
import com.cpu.quikdata.feature.createform.foodsecurityinfo.foodsecuritycoping.FoodSecurityCopingViewModel
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap

@Module
abstract class FoodSecurityCopingModule {

    @Binds
    @IntoMap
    @ViewModelKey(FoodSecurityCopingViewModel::class)
    abstract fun bindsFoodSecurityCopingViewModel(foodSecurityCopingViewModel: FoodSecurityCopingViewModel): ViewModel

    companion object {

        @Provides
        fun provideFoodSecurityCopingRepository(application: Application, formId: String)
                : FoodSecurityCopingRepository {
            return FoodSecurityCopingRepository(application, formId)
        }
    }
}