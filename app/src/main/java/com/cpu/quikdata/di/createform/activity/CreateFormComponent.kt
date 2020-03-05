package com.cpu.quikdata.di.createform.activity

import com.cpu.quikdata.base.BaseCreateFormFragment
import com.cpu.quikdata.di.ActivityScope
import com.cpu.quikdata.di.createform.formdetailsandbaseline.FormDetailsAndBaselineComponent
import com.cpu.quikdata.feature.createform.activity.CreateFormActivity
import dagger.BindsInstance
import dagger.Subcomponent

@ActivityScope
@Subcomponent(modules = [CreateFormModule::class, CreateFormSubcomponents::class])
interface CreateFormComponent {

    @Subcomponent.Factory
    interface Factory {
        fun create(@BindsInstance createFormActivity: CreateFormActivity): CreateFormComponent
    }

    fun formDetailsAndBaselineComponent(): FormDetailsAndBaselineComponent.Factory

    fun inject(createFormActivity: CreateFormActivity)

    fun inject(createFormFragment: BaseCreateFormFragment)
}