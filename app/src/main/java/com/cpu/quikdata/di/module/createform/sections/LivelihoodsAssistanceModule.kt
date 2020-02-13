package com.cpu.quikdata.di.module.createform.sections

import androidx.lifecycle.ViewModel
import com.cpu.quikdata.data.AppDatabase
import com.cpu.quikdata.di.annotation.FormIdQualifier
import com.cpu.quikdata.di.annotation.ViewModelKey
import com.cpu.quikdata.feature.createform.livelihoodsinfo.livelihoodsassistance.LivelihoodsAssistanceRepository
import com.cpu.quikdata.feature.createform.livelihoodsinfo.livelihoodsassistance.LivelihoodsAssistanceViewModel
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap

@Module
abstract class LivelihoodsAssistanceModule {

    @Binds
    @IntoMap
    @ViewModelKey(LivelihoodsAssistanceViewModel::class)
    abstract fun bindsLivelihoodsAssistanceViewModel(livelihoodsAssistanceViewModel: LivelihoodsAssistanceViewModel): ViewModel

    companion object {

        @Provides
        fun provideLivelihoodsAssistanceRepository(database: AppDatabase, @FormIdQualifier formId: String)
                : LivelihoodsAssistanceRepository {
            return LivelihoodsAssistanceRepository(database, formId)
        }
    }
}