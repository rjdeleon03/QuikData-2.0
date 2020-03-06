package com.cpu.quikdata.di.createform.foodsecurityinfo

import androidx.lifecycle.ViewModel
import com.cpu.quikdata.di.app.module.ViewModelKey
import com.cpu.quikdata.feature.createform.foodsecurityinfo.foodsecurityassistance.FoodSecurityAssistanceViewModel
import com.cpu.quikdata.feature.createform.foodsecurityinfo.foodsecuritycoping.FoodSecurityCopingViewModel
import com.cpu.quikdata.feature.createform.foodsecurityinfo.foodsecuritygaps.FoodSecurityGapsViewModel
import com.cpu.quikdata.feature.createform.foodsecurityinfo.foodsecurityimpact.FoodSecurityImpactViewModel
import com.cpu.quikdata.feature.createform.foodsecurityinfo.foodsecurityneeds.FoodSecurityNeedsViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class FoodSecurityInfoModule {

    @Binds
    @IntoMap
    @ViewModelKey(FoodSecurityImpactViewModel::class)
    abstract fun bindFoodSecurityImpactViewModel(foodSecurityImpactViewModel: FoodSecurityImpactViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(FoodSecurityCopingViewModel::class)
    abstract fun bindFoodSecurityCopingViewModel(foodSecurityCopingViewModel: FoodSecurityCopingViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(FoodSecurityNeedsViewModel::class)
    abstract fun bindFoodSecurityNeedsViewModel(foodSecurityNeedsViewModel: FoodSecurityNeedsViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(FoodSecurityAssistanceViewModel::class)
    abstract fun bindFoodSecurityAssistanceViewModel(foodSecurityAssistanceViewModel: FoodSecurityAssistanceViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(FoodSecurityGapsViewModel::class)
    abstract fun bindFoodSecurityGapsViewModel(foodSecurityGapsViewModel: FoodSecurityGapsViewModel): ViewModel
}