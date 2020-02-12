package com.cpu.quikdata.di.module.createform.sections

import android.app.Application
import androidx.lifecycle.ViewModel
import com.cpu.quikdata.di.annotation.ViewModelKey
import com.cpu.quikdata.feature.createform.generalinfo.calamityinfo.CalamityInfoRepository
import com.cpu.quikdata.feature.createform.generalinfo.calamityinfo.CalamityInfoViewModel
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap

@Module
abstract class CalamityInfoModule {

    @Binds
    @IntoMap
    @ViewModelKey(CalamityInfoViewModel::class)
    abstract fun bindsCalamityInfoViewModel(calamityInfoViewModel: CalamityInfoViewModel): ViewModel

    companion object {

        @Provides
        fun provideCalamityInfoRepository(application: Application, formId: String)
                : CalamityInfoRepository {
            return CalamityInfoRepository(application, formId)
        }
    }
}