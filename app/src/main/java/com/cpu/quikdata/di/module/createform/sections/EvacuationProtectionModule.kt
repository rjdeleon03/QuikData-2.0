package com.cpu.quikdata.di.module.createform.sections

import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import com.cpu.quikdata.data.AppDatabase
import com.cpu.quikdata.di.annotation.EvacuationIdQualifier
import com.cpu.quikdata.di.annotation.ViewModelKey
import com.cpu.quikdata.feature.createform.evacuationinfo.evacuationprotection.EvacuationProtectionFragment
import com.cpu.quikdata.feature.createform.evacuationinfo.evacuationprotection.EvacuationProtectionRepository
import com.cpu.quikdata.feature.createform.evacuationinfo.evacuationprotection.EvacuationProtectionViewModel
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap

@Module
abstract class EvacuationProtectionModule {

    @Binds
    abstract fun bindsEvacuationProtectionFragment(fragment: EvacuationProtectionFragment): Fragment

    @Binds
    @IntoMap
    @ViewModelKey(EvacuationProtectionViewModel::class)
    abstract fun bindsEvacuationProtectionViewModel(evacuationProtectionViewModel: EvacuationProtectionViewModel): ViewModel

    companion object {

        @Provides
        fun provideEvacuationProtectionRepository(database: AppDatabase, @EvacuationIdQualifier evacuationId: String)
                : EvacuationProtectionRepository {
            return EvacuationProtectionRepository(database, evacuationId)
        }
    }
}