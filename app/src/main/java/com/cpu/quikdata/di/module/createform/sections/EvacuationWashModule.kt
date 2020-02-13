package com.cpu.quikdata.di.module.createform.sections

import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import com.cpu.quikdata.data.AppDatabase
import com.cpu.quikdata.di.annotation.EvacuationIdQualifier
import com.cpu.quikdata.di.annotation.ViewModelKey
import com.cpu.quikdata.feature.createform.evacuationinfo.evacuationwash.EvacuationWashFragment
import com.cpu.quikdata.feature.createform.evacuationinfo.evacuationwash.EvacuationWashRepository
import com.cpu.quikdata.feature.createform.evacuationinfo.evacuationwash.EvacuationWashViewModel
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap

@Module
abstract class EvacuationWashModule {

    @Binds
    abstract fun bindsEvacuationWashFragment(fragment: EvacuationWashFragment): Fragment

    @Binds
    @IntoMap
    @ViewModelKey(EvacuationWashViewModel::class)
    abstract fun bindsEvacuationWashViewModel(evacuationWashViewModel: EvacuationWashViewModel): ViewModel

    companion object {

        @Provides
        fun provideEvacuationWashRepository(database: AppDatabase, @EvacuationIdQualifier evacuationId: String)
                : EvacuationWashRepository {
            return EvacuationWashRepository(database, evacuationId)
        }
    }
}