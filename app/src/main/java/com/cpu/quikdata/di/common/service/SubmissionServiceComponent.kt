package com.cpu.quikdata.di.common.service

import com.cpu.quikdata.common.service.SubmissionService
import dagger.BindsInstance
import dagger.Subcomponent

@Subcomponent
interface SubmissionServiceComponent {

    @Subcomponent.Factory
    interface Factory {
        fun create(@BindsInstance submissionService: SubmissionService): SubmissionServiceComponent
    }

    fun inject(submissionService: SubmissionService)
}