package com.cpu.quikdata.di.createform.formdetailsandbaseline

import com.cpu.quikdata.feature.createform.formdetailsandbaseline.baselinedata.BaselineDataFragment
import com.cpu.quikdata.feature.createform.formdetailsandbaseline.formdetails.FormDetailsFragment
import dagger.Subcomponent

@Subcomponent(modules = [FormDetailsAndBaselineModule::class])
interface FormDetailsAndBaselineComponent {

    @Subcomponent.Factory
    interface Factory {
        fun create(): FormDetailsAndBaselineComponent
    }

    fun inject(formDetailsFragment: FormDetailsFragment)

    fun inject(baselineDataFragment: BaselineDataFragment)
}