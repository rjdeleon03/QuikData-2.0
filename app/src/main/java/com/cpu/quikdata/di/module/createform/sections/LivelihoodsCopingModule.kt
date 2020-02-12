package com.cpu.quikdata.di.module.createform.sections

import android.app.Application
import androidx.lifecycle.ViewModel
import com.cpu.quikdata.di.annotation.ViewModelKey
import com.cpu.quikdata.feature.createform.livelihoodsinfo.livelihoodscoping.LivelihoodsCopingRepository
import com.cpu.quikdata.feature.createform.livelihoodsinfo.livelihoodscoping.LivelihoodsCopingViewModel
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap

@Module
abstract class LivelihoodsCopingModule {

    @Binds
    @IntoMap
    @ViewModelKey(LivelihoodsCopingViewModel::class)
    abstract fun bindsLivelihoodsCopingViewModel(livelihoodsCopingViewModel: LivelihoodsCopingViewModel): ViewModel

    companion object {

        @Provides
        fun provideLivelihoodsCopingRepository(application: Application, formId: String)
                : LivelihoodsCopingRepository {
            return LivelihoodsCopingRepository(application, formId)
        }
    }
}