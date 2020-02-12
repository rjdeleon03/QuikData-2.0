package com.cpu.quikdata.di.module.createform.sections

import android.app.Application
import androidx.lifecycle.ViewModel
import com.cpu.quikdata.di.annotation.ViewModelKey
import com.cpu.quikdata.feature.createform.foodsecurityinfo.foodsecurityneeds.FoodSecurityNeedsRepository
import com.cpu.quikdata.feature.createform.foodsecurityinfo.foodsecurityneeds.FoodSecurityNeedsViewModel
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap

@Module
abstract class FoodSecurityNeedsModule {

    @Binds
    @IntoMap
    @ViewModelKey(FoodSecurityNeedsViewModel::class)
    abstract fun bindsFoodSecurityNeedsViewModel(foodSecurityNeedsViewModel: FoodSecurityNeedsViewModel): ViewModel

    companion object {

        @Provides
        fun provideFoodSecurityNeedsRepository(application: Application, formId: String)
                : FoodSecurityNeedsRepository {
            return FoodSecurityNeedsRepository(application, formId)
        }
    }
}