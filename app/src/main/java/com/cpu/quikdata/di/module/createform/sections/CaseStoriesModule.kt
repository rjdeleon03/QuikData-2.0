package com.cpu.quikdata.di.module.createform.sections

import android.app.Application
import androidx.lifecycle.ViewModel
import com.cpu.quikdata.di.annotation.FormIdQualifier
import com.cpu.quikdata.di.annotation.ViewModelKey
import com.cpu.quikdata.feature.createform.casestories.CaseStoriesRepository
import com.cpu.quikdata.feature.createform.casestories.CaseStoriesViewModel
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap

@Module
abstract class CaseStoriesModule {

    @Binds
    @IntoMap
    @ViewModelKey(CaseStoriesViewModel::class)
    abstract fun bindsCaseStoriesViewModel(caseStoriesViewModel: CaseStoriesViewModel): ViewModel

    companion object {

        @Provides
        fun provideCaseStoriesRepository(application: Application, @FormIdQualifier formId: String)
                : CaseStoriesRepository {
            return CaseStoriesRepository(application, formId)
        }
    }
}