package com.cpu.quikdata.di.createform.casestories

import androidx.lifecycle.ViewModel
import com.cpu.quikdata.di.app.module.ViewModelKey
import com.cpu.quikdata.feature.createform.casestories.CaseStoriesViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class CaseStoriesModule {

    @Binds
    @IntoMap
    @ViewModelKey(CaseStoriesViewModel::class)
    abstract fun bindCaseStoriesViewModel(caseStoriesViewModel: CaseStoriesViewModel): ViewModel
}