package com.cpu.quikdata.di.module.createform.sections

import android.app.Application
import androidx.lifecycle.ViewModel
import com.cpu.quikdata.di.annotation.ViewModelKey
import com.cpu.quikdata.feature.createform.healthinfo.specialneeds.SpecialNeedsRepository
import com.cpu.quikdata.feature.createform.healthinfo.specialneeds.SpecialNeedsViewModel
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap

@Module
abstract class SpecialNeedsModule {

    @Binds
    @IntoMap
    @ViewModelKey(SpecialNeedsViewModel::class)
    abstract fun bindsSpecialNeedsViewModel(specialNeedsViewModel: SpecialNeedsViewModel): ViewModel

    companion object {

        @Provides
        fun provideSpecialNeedsRepository(application: Application, formId: String)
                : SpecialNeedsRepository {
            return SpecialNeedsRepository(application, formId)
        }
    }
}