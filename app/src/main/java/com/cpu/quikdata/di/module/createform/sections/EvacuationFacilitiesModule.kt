package com.cpu.quikdata.di.module.createform.sections

import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import com.cpu.quikdata.data.AppDatabase
import com.cpu.quikdata.di.annotation.EvacuationIdQualifier
import com.cpu.quikdata.di.annotation.ViewModelKey
import com.cpu.quikdata.feature.createform.evacuationinfo.evacuationfacilities.EvacuationFacilitiesFragment
import com.cpu.quikdata.feature.createform.evacuationinfo.evacuationfacilities.EvacuationFacilitiesRepository
import com.cpu.quikdata.feature.createform.evacuationinfo.evacuationfacilities.EvacuationFacilitiesViewModel
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap

@Module
abstract class EvacuationFacilitiesModule {

    @Binds
    abstract fun bindsEvacuationFacilitiesFragment(fragment: EvacuationFacilitiesFragment): Fragment

    @Binds
    @IntoMap
    @ViewModelKey(EvacuationFacilitiesViewModel::class)
    abstract fun bindsEvacuationFacilitiesViewModel(evacuationFacilitiesViewModel: EvacuationFacilitiesViewModel): ViewModel

    companion object {

        @Provides
        fun provideEvacuationFacilitiesRepository(database: AppDatabase, @EvacuationIdQualifier evacuationId: String)
                : EvacuationFacilitiesRepository {
            return EvacuationFacilitiesRepository(database, evacuationId)
        }
    }
}