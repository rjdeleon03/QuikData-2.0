package com.cpu.quikdata.di.createform.activity

import androidx.lifecycle.ViewModel
import com.cpu.quikdata.di.ActivityScope
import com.cpu.quikdata.di.app.module.ViewModelKey
import com.cpu.quikdata.feature.createform.activity.CreateFormActivity
import com.cpu.quikdata.feature.createform.activity.CreateFormViewModel
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap

@Module
abstract class CreateFormModule {

    @ActivityScope
    @Binds
    @IntoMap
    @ViewModelKey(CreateFormViewModel::class)
    abstract fun bindCreateFormViewModel(createFormViewModel: CreateFormViewModel): ViewModel

    companion object {

        @Provides
        fun getFormId(createFormActivity: CreateFormActivity): String {
            return createFormActivity.intent.getStringExtra(CreateFormActivity.FORM_ID_KEY)
        }
    }
}