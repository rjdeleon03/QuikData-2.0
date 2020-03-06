package com.cpu.quikdata.di.createform.watersanitationinfo

import com.cpu.quikdata.feature.createform.watersanitationinfo.washassistance.WashAssistanceFragment
import com.cpu.quikdata.feature.createform.watersanitationinfo.washconditions.WashConditionsFragment
import com.cpu.quikdata.feature.createform.watersanitationinfo.washcoping.WashCopingFragment
import com.cpu.quikdata.feature.createform.watersanitationinfo.washgaps.WashGapsFragment
import dagger.Subcomponent

@Subcomponent
interface WaterSanitationInfoComponent {

    @Subcomponent.Factory
    interface Factory {
        fun create(): WaterSanitationInfoComponent
    }

    fun inject(washConditionsFragment: WashConditionsFragment)
    fun inject(washCopingFragment: WashCopingFragment)
    fun inject(washAssistanceFragment: WashAssistanceFragment)
    fun inject(washGapsFragment: WashGapsFragment)
}