package com.cpu.quikdata.di.createform.submission

import com.cpu.quikdata.feature.createform.base.BaseSubmissionFragment
import dagger.Subcomponent

@Subcomponent(modules = [SubmissionModule::class])
interface SubmissionComponent {

    @Subcomponent.Factory
    interface Factory {
        fun create(): SubmissionComponent
    }

    fun inject(submissionFragment: BaseSubmissionFragment)
}