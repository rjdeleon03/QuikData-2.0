package com.cpu.quikdata.di.module.createform.sections

import android.app.Application
import androidx.lifecycle.ViewModel
import com.cpu.quikdata.di.annotation.FormIdQualifier
import com.cpu.quikdata.di.annotation.ViewModelKey
import com.cpu.quikdata.feature.createform.evacuationinfo.EvacuationInfoRepository
import com.cpu.quikdata.feature.createform.evacuationinfo.EvacuationInfoViewModel
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap

@Module
abstract class EvacuationInfoModule {

    @Binds
    @IntoMap
    @ViewModelKey(EvacuationInfoViewModel::class)
    abstract fun bindsEvacuationInfoViewModel(evacuationInfoViewModel: EvacuationInfoViewModel): ViewModel

    companion object {

        @Provides
        fun provideEvacuationInfoRepository(application: Application, @FormIdQualifier formId: String)
                : EvacuationInfoRepository {
            return EvacuationInfoRepository(application, formId)
        }
    }
}