package com.cpu.quikdata.di.createform.livelihoodsinfo

import androidx.lifecycle.ViewModel
import com.cpu.quikdata.di.app.module.ViewModelKey
import com.cpu.quikdata.feature.createform.livelihoodsinfo.estimateddamage.EstimatedDamageViewModel
import com.cpu.quikdata.feature.createform.livelihoodsinfo.incomeafter.IncomeAfterViewModel
import com.cpu.quikdata.feature.createform.livelihoodsinfo.incomebefore.IncomeBeforeViewModel
import com.cpu.quikdata.feature.createform.livelihoodsinfo.livelihoodsassistance.LivelihoodsAssistanceViewModel
import com.cpu.quikdata.feature.createform.livelihoodsinfo.livelihoodscoping.LivelihoodsCopingViewModel
import com.cpu.quikdata.feature.createform.livelihoodsinfo.livelihoodsgaps.LivelihoodsGapsViewModel
import com.cpu.quikdata.feature.createform.livelihoodsinfo.livelihoodsneeds.LivelihoodsNeedsViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class LivelihoodsInfoModule {

    @Binds
    @IntoMap
    @ViewModelKey(IncomeBeforeViewModel::class)
    abstract fun bindIncomeBeforeViewModel(incomeBeforeViewModel: IncomeBeforeViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(IncomeAfterViewModel::class)
    abstract fun bindIncomeAfterViewModel(incomeAfterViewModel: IncomeAfterViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(EstimatedDamageViewModel::class)
    abstract fun bindEstimatedDamageViewModel(estimatedDamageViewModel: EstimatedDamageViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(LivelihoodsCopingViewModel::class)
    abstract fun bindLivelihoodsCopingViewModel(livelihoodsCopingViewModel: LivelihoodsCopingViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(LivelihoodsNeedsViewModel::class)
    abstract fun bindLivelihoodsNeedsViewModel(livelihoodsNeedsViewModel: LivelihoodsNeedsViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(LivelihoodsAssistanceViewModel::class)
    abstract fun bindLivelihoodsAssistanceViewModel(livelihoodsAssistanceViewModel: LivelihoodsAssistanceViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(LivelihoodsGapsViewModel::class)
    abstract fun bindLivelihoodsGapsViewModel(livelihoodsGapsViewModel: LivelihoodsGapsViewModel): ViewModel
}