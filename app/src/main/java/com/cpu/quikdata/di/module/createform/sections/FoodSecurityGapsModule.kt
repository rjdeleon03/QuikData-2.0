package com.cpu.quikdata.di.module.createform.sections

import androidx.lifecycle.ViewModel
import com.cpu.quikdata.data.AppDatabase
import com.cpu.quikdata.di.annotation.FormIdQualifier
import com.cpu.quikdata.di.annotation.ViewModelKey
import com.cpu.quikdata.feature.createform.foodsecurityinfo.foodsecuritygaps.FoodSecurityGapsRepository
import com.cpu.quikdata.feature.createform.foodsecurityinfo.foodsecuritygaps.FoodSecurityGapsViewModel
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap

@Module
abstract class FoodSecurityGapsModule {

    @Binds
    @IntoMap
    @ViewModelKey(FoodSecurityGapsViewModel::class)
    abstract fun bindsFoodSecurityGapsViewModel(foodSecurityGapsViewModel: FoodSecurityGapsViewModel): ViewModel

    companion object {

        @Provides
        fun provideFoodSecurityGapsRepository(database: AppDatabase, @FormIdQualifier formId: String)
                : FoodSecurityGapsRepository {
            return FoodSecurityGapsRepository(database, formId)
        }
    }
}