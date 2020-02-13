package com.cpu.quikdata.di.module.createform.sections

import android.app.Application
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import com.cpu.quikdata.di.annotation.EvacuationIdQualifier
import com.cpu.quikdata.di.annotation.ViewModelKey
import com.cpu.quikdata.feature.createform.evacuationinfo.evacuationcoping.EvacuationCopingFragment
import com.cpu.quikdata.feature.createform.evacuationinfo.evacuationcoping.EvacuationCopingRepository
import com.cpu.quikdata.feature.createform.evacuationinfo.evacuationcoping.EvacuationCopingViewModel
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap

@Module
abstract class EvacuationCopingModule {

    @Binds
    abstract fun bindsEvacuationCopingFragment(fragment: EvacuationCopingFragment): Fragment

    @Binds
    @IntoMap
    @ViewModelKey(EvacuationCopingViewModel::class)
    abstract fun bindsEvacuationCopingViewModel(evacuationCopingViewModel: EvacuationCopingViewModel): ViewModel

    companion object {

        @Provides
        fun provideEvacuationCopingRepository(application: Application, @EvacuationIdQualifier evacuationId: String)
                : EvacuationCopingRepository {
            return EvacuationCopingRepository(application, evacuationId)
        }
    }
}