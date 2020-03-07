package com.cpu.quikdata.di.createform.evacuationinfo.evacuationitem

import com.cpu.quikdata.di.EvacuationItemScope
import com.cpu.quikdata.feature.createform.evacuationinfo.container.EvacuationContainerFragment
import com.cpu.quikdata.feature.createform.evacuationinfo.evacuationage.EvacuationAgeFragment
import com.cpu.quikdata.feature.createform.evacuationinfo.evacuationcoping.EvacuationCopingFragment
import com.cpu.quikdata.feature.createform.evacuationinfo.evacuationfacilities.EvacuationFacilitiesFragment
import com.cpu.quikdata.feature.createform.evacuationinfo.evacuationprotection.EvacuationProtectionFragment
import com.cpu.quikdata.feature.createform.evacuationinfo.evacuationwash.EvacuationWashFragment
import com.cpu.quikdata.feature.createform.evacuationinfo.siteinfo.SiteInfoFragment
import dagger.BindsInstance
import dagger.Subcomponent

@EvacuationItemScope
@Subcomponent(modules = [EvacuationItemModule::class])
interface EvacuationItemComponent {

    @Subcomponent.Factory
    interface Factory {
        fun create(@BindsInstance evacuationContainerFragment: EvacuationContainerFragment): EvacuationItemComponent
    }

    fun inject(siteInfoFragment: SiteInfoFragment)
    fun inject(evacuationAgeFragment: EvacuationAgeFragment)
    fun inject(evacuationFacilitiesFragment: EvacuationFacilitiesFragment)
    fun inject(evacuationWashFragment: EvacuationWashFragment)
    fun inject(evacuationProtectionFragment: EvacuationProtectionFragment)
    fun inject(evacuationCopingFragment: EvacuationCopingFragment)
}