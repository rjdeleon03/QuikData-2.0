package com.cpu.quikdata.di.createform.submission

import androidx.lifecycle.ViewModel
import com.cpu.quikdata.di.app.module.ViewModelKey
import com.cpu.quikdata.feature.createform.base.SubmissionViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class SubmissionModule {

    @Binds
    @IntoMap
    @ViewModelKey(SubmissionViewModel::class)
    abstract fun bindSubmissionViewModel(submissionViewModel: SubmissionViewModel): ViewModel
}