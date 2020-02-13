package com.cpu.quikdata.di.module.createform.sections

import androidx.lifecycle.ViewModel
import com.cpu.quikdata.data.AppDatabase
import com.cpu.quikdata.di.annotation.FormIdQualifier
import com.cpu.quikdata.di.annotation.ViewModelKey
import com.cpu.quikdata.feature.createform.healthinfo.psychosocial.PsychosocialRepository
import com.cpu.quikdata.feature.createform.healthinfo.psychosocial.PsychosocialViewModel
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap

@Module
abstract class PsychosocialModule {

    @Binds
    @IntoMap
    @ViewModelKey(PsychosocialViewModel::class)
    abstract fun bindsPsychosocialViewModel(psychosocialViewModel: PsychosocialViewModel): ViewModel

    companion object {

        @Provides
        fun providePsychosocialRepository(database: AppDatabase, @FormIdQualifier formId: String)
                : PsychosocialRepository {
            return PsychosocialRepository(database, formId)
        }
    }
}