package com.cpu.quikdata.di.createform.evacuationinfo.evacuationitem

import androidx.lifecycle.ViewModel
import com.cpu.quikdata.di.EvacuationId
import com.cpu.quikdata.di.EvacuationItemScope
import com.cpu.quikdata.di.app.module.ViewModelKey
import com.cpu.quikdata.feature.createform.evacuationinfo.container.EvacuationContainerFragment
import com.cpu.quikdata.feature.createform.evacuationinfo.evacuationage.EvacuationAgeViewModel
import com.cpu.quikdata.feature.createform.evacuationinfo.evacuationcoping.EvacuationCopingViewModel
import com.cpu.quikdata.feature.createform.evacuationinfo.evacuationfacilities.EvacuationFacilitiesViewModel
import com.cpu.quikdata.feature.createform.evacuationinfo.evacuationprotection.EvacuationProtectionViewModel
import com.cpu.quikdata.feature.createform.evacuationinfo.evacuationwash.EvacuationWashViewModel
import com.cpu.quikdata.feature.createform.evacuationinfo.siteinfo.SiteInfoViewModel
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap

@Module
abstract class EvacuationItemModule {

    @Binds
    @IntoMap
    @ViewModelKey(SiteInfoViewModel::class)
    abstract fun bindSiteInfoViewModel(siteInfoViewModel: SiteInfoViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(EvacuationAgeViewModel::class)
    abstract fun bindEvacuationAgeViewModel(evacuationAgeViewModel: EvacuationAgeViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(EvacuationFacilitiesViewModel::class)
    abstract fun bindEvacuationFacilitiesViewModel(evacuationFacilitiesViewModel: EvacuationFacilitiesViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(EvacuationWashViewModel::class)
    abstract fun bindEvacuationWashViewModel(evacuationWashViewModel: EvacuationWashViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(EvacuationProtectionViewModel::class)
    abstract fun bindEvacuationProtectionViewModel(evacuationProtectionViewModel: EvacuationProtectionViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(EvacuationCopingViewModel::class)
    abstract fun bindEvacuationCopingViewModel(evacuationCopingViewModel: EvacuationCopingViewModel): ViewModel

    companion object {

        @EvacuationItemScope
        @EvacuationId
        @Provides
        fun provideEvacuationId(evacuationContainerFragment: EvacuationContainerFragment): String {
            return evacuationContainerFragment.args.evacuationId
        }
    }
}