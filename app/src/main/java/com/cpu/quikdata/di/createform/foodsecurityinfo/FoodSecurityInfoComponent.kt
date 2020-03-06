package com.cpu.quikdata.di.createform.foodsecurityinfo

import com.cpu.quikdata.feature.createform.foodsecurityinfo.foodsecurityassistance.FoodSecurityAssistanceFragment
import com.cpu.quikdata.feature.createform.foodsecurityinfo.foodsecuritycoping.FoodSecurityCopingFragment
import com.cpu.quikdata.feature.createform.foodsecurityinfo.foodsecuritygaps.FoodSecurityGapsFragment
import com.cpu.quikdata.feature.createform.foodsecurityinfo.foodsecurityimpact.FoodSecurityImpactFragment
import com.cpu.quikdata.feature.createform.foodsecurityinfo.foodsecurityneeds.FoodSecurityNeedsFragment
import dagger.Subcomponent

@Subcomponent(modules = [FoodSecurityInfoModule::class])
interface FoodSecurityInfoComponent {

    @Subcomponent.Factory
    interface Factory {
        fun create(): FoodSecurityInfoComponent
    }

    fun inject(foodSecurityImpactFragment: FoodSecurityImpactFragment)
    fun inject(foodSecurityCopingFragment: FoodSecurityCopingFragment)
    fun inject(foodSecurityNeedsViewModel: FoodSecurityNeedsFragment)
    fun inject(foodSecurityAssistanceViewModel: FoodSecurityAssistanceFragment)
    fun inject(foodSecurityGapsViewModel: FoodSecurityGapsFragment)
}