package com.cpu.quikdata.di.module.createform.sections

import androidx.lifecycle.ViewModel
import com.cpu.quikdata.data.AppDatabase
import com.cpu.quikdata.di.annotation.FormIdQualifier
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
        fun provideFoodSecurityCopingRepository(database: AppDatabase, @FormIdQualifier formId: String)
                : FoodSecurityCopingRepository {
            return FoodSecurityCopingRepository(database, formId)
        }
    }
}