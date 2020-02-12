package com.cpu.quikdata.di.module.createform.sections

import android.app.Application
import androidx.lifecycle.ViewModel
import com.cpu.quikdata.di.annotation.ViewModelKey
import com.cpu.quikdata.feature.createform.livelihoodsinfo.livelihoodsneeds.LivelihoodsNeedsRepository
import com.cpu.quikdata.feature.createform.livelihoodsinfo.livelihoodsneeds.LivelihoodsNeedsViewModel
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap

@Module
abstract class LivelihoodsNeedsModule {

    @Binds
    @IntoMap
    @ViewModelKey(LivelihoodsNeedsViewModel::class)
    abstract fun bindsLivelihoodsNeedsViewModel(livelihoodsNeedsViewModel: LivelihoodsNeedsViewModel): ViewModel

    companion object {

        @Provides
        fun provideLivelihoodsNeedsRepository(application: Application, formId: String)
                : LivelihoodsNeedsRepository {
            return LivelihoodsNeedsRepository(application, formId)
        }
    }
}