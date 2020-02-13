package com.cpu.quikdata.di.module.createform.sections

import androidx.lifecycle.ViewModel
import com.cpu.quikdata.data.AppDatabase
import com.cpu.quikdata.di.annotation.FormIdQualifier
import com.cpu.quikdata.di.annotation.ViewModelKey
import com.cpu.quikdata.feature.createform.formdetailsandbaseline.formdetails.FormDetailsRepository
import com.cpu.quikdata.feature.createform.formdetailsandbaseline.formdetails.FormDetailsViewModel
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap

@Module
abstract class FormDetailsModule {

    @Binds
    @IntoMap
    @ViewModelKey(FormDetailsViewModel::class)
    abstract fun bindsFormDetailsViewModel(formDetailsViewModel: FormDetailsViewModel): ViewModel

    companion object {

        @Provides
        fun provideFormDetailsRepository(database: AppDatabase, @FormIdQualifier formId: String)
                : FormDetailsRepository {
            return FormDetailsRepository(database, formId)
        }
    }
}