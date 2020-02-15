package com.cpu.quikdata.di.module.createform.sections

import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import com.cpu.quikdata.data.AppDatabase
import com.cpu.quikdata.di.annotation.EvacuationIdQualifier
import com.cpu.quikdata.di.annotation.ViewModelKey
import com.cpu.quikdata.feature.createform.evacuationinfo.evacuationage.EvacuationAgeFragment
import com.cpu.quikdata.feature.createform.evacuationinfo.evacuationage.EvacuationAgeRepository
import com.cpu.quikdata.feature.createform.evacuationinfo.evacuationage.EvacuationAgeViewModel
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap

@Module
abstract class EvacuationAgeModule {

    @Binds
    abstract fun bindsEvacuationAgeFragment(fragment: EvacuationAgeFragment): Fragment

    @Binds
    @IntoMap
    @ViewModelKey(EvacuationAgeViewModel::class)
    abstract fun bindsEvacuationAgeViewModel(evacuationAgeViewModel: EvacuationAgeViewModel): ViewModel

    companion object {

        @Provides
        fun provideEvacuationAgeRepository(database: AppDatabase, @EvacuationIdQualifier evacuationId: String)
                : EvacuationAgeRepository {
            return EvacuationAgeRepository(database, evacuationId)
        }
    }
}