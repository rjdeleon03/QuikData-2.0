package com.cpu.quikdata.di.module.createform.sections

import androidx.lifecycle.ViewModel
import com.cpu.quikdata.data.AppDatabase
import com.cpu.quikdata.di.annotation.FormIdQualifier
import com.cpu.quikdata.di.annotation.ViewModelKey
import com.cpu.quikdata.feature.createform.foodsecurityinfo.foodsecurityimpact.FoodSecurityImpactRepository
import com.cpu.quikdata.feature.createform.foodsecurityinfo.foodsecurityimpact.FoodSecurityImpactViewModel
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap

@Module
abstract class FoodSecurityImpactModule {

    @Binds
    @IntoMap
    @ViewModelKey(FoodSecurityImpactViewModel::class)
    abstract fun bindsFoodSecurityImpactViewModel(foodSecurityImpactViewModel: FoodSecurityImpactViewModel): ViewModel

    companion object {

        @Provides
        fun provideFoodSecurityImpactRepository(database: AppDatabase, @FormIdQualifier formId: String)
                : FoodSecurityImpactRepository {
            return FoodSecurityImpactRepository(database, formId)
        }
    }
}