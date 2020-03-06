package com.cpu.quikdata.di.createform.livelihoodsinfo

import com.cpu.quikdata.feature.createform.livelihoodsinfo.estimateddamage.EstimatedDamageFragment
import com.cpu.quikdata.feature.createform.livelihoodsinfo.incomeafter.IncomeAfterFragment
import com.cpu.quikdata.feature.createform.livelihoodsinfo.incomebefore.IncomeBeforeFragment
import com.cpu.quikdata.feature.createform.livelihoodsinfo.livelihoodsassistance.LivelihoodsAssistanceFragment
import com.cpu.quikdata.feature.createform.livelihoodsinfo.livelihoodscoping.LivelihoodsCopingFragment
import com.cpu.quikdata.feature.createform.livelihoodsinfo.livelihoodsgaps.LivelihoodsGapsFragment
import com.cpu.quikdata.feature.createform.livelihoodsinfo.livelihoodsneeds.LivelihoodsNeedsFragment
import dagger.Subcomponent

@Subcomponent(modules = [LivelihoodsInfoModule::class])
interface LivelihoodsInfoComponent {

    @Subcomponent.Factory
    interface Factory {
        fun create(): LivelihoodsInfoComponent
    }

    fun inject(incomeBeforeFragment: IncomeBeforeFragment)
    fun inject(incomeAfterFragment: IncomeAfterFragment)
    fun inject(estimatedDamageFragment: EstimatedDamageFragment)
    fun inject(livelihoodsCopingFragment: LivelihoodsCopingFragment)
    fun inject(livelihoodsNeedsFragment: LivelihoodsNeedsFragment)
    fun inject(livelihoodsAssistanceFragment: LivelihoodsAssistanceFragment)
    fun inject(livelihoodsGapsFragment: LivelihoodsGapsFragment)
}