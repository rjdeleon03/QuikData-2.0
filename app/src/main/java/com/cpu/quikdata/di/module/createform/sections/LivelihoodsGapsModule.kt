package com.cpu.quikdata.di.module.createform.sections

import androidx.lifecycle.ViewModel
import com.cpu.quikdata.data.AppDatabase
import com.cpu.quikdata.di.annotation.FormIdQualifier
import com.cpu.quikdata.di.annotation.ViewModelKey
import com.cpu.quikdata.feature.createform.livelihoodsinfo.livelihoodsgaps.LivelihoodsGapsRepository
import com.cpu.quikdata.feature.createform.livelihoodsinfo.livelihoodsgaps.LivelihoodsGapsViewModel
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap

@Module
abstract class LivelihoodsGapsModule {

    @Binds
    @IntoMap
    @ViewModelKey(LivelihoodsGapsViewModel::class)
    abstract fun bindsLivelihoodsGapsViewModel(livelihoodsGapsViewModel: LivelihoodsGapsViewModel): ViewModel

    companion object {

        @Provides
        fun provideLivelihoodsGapsRepository(database: AppDatabase, @FormIdQualifier formId: String)
                : LivelihoodsGapsRepository {
            return LivelihoodsGapsRepository(database, formId)
        }
    }
}