package com.cpu.quikdata.di.module.createform.sections

import androidx.lifecycle.ViewModel
import com.cpu.quikdata.data.AppDatabase
import com.cpu.quikdata.di.annotation.FormIdQualifier
import com.cpu.quikdata.di.annotation.ViewModelKey
import com.cpu.quikdata.feature.createform.foodsecurityinfo.foodsecurityassistance.FoodSecurityAssistanceRepository
import com.cpu.quikdata.feature.createform.foodsecurityinfo.foodsecurityassistance.FoodSecurityAssistanceViewModel
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap

@Module
abstract class FoodSecurityAssistanceModule {

    @Binds
    @IntoMap
    @ViewModelKey(FoodSecurityAssistanceViewModel::class)
    abstract fun bindsFoodSecurityAssistanceViewModel(foodSecurityAssistanceViewModel: FoodSecurityAssistanceViewModel): ViewModel

    companion object {

        @Provides
        fun provideFoodSecurityAssistanceRepository(database: AppDatabase, @FormIdQualifier formId: String)
                : FoodSecurityAssistanceRepository {
            return FoodSecurityAssistanceRepository(database, formId)
        }
    }
}