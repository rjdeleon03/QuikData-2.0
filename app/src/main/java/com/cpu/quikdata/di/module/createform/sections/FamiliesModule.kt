package com.cpu.quikdata.di.module.createform.sections

import android.app.Application
import androidx.lifecycle.ViewModel
import com.cpu.quikdata.di.annotation.FormIdQualifier
import com.cpu.quikdata.di.annotation.ViewModelKey
import com.cpu.quikdata.feature.createform.generalinfo.families.FamiliesRepository
import com.cpu.quikdata.feature.createform.generalinfo.families.FamiliesViewModel
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap

@Module
abstract class FamiliesModule {

    @Binds
    @IntoMap
    @ViewModelKey(FamiliesViewModel::class)
    abstract fun bindsFamiliesViewModel(familiesViewModel: FamiliesViewModel): ViewModel

    companion object {

        @Provides
        fun provideFamiliesRepository(application: Application, @FormIdQualifier formId: String)
                : FamiliesRepository {
            return FamiliesRepository(application, formId)
        }
    }
}