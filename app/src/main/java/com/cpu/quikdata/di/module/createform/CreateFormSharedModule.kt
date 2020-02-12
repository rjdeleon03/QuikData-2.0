package com.cpu.quikdata.di.module.createform

import android.app.Activity
import android.app.Application
import android.os.Bundle
import androidx.lifecycle.ViewModel
import com.cpu.quikdata.di.annotation.CreateFormActivityScope
import com.cpu.quikdata.di.annotation.ViewModelKey
import com.cpu.quikdata.feature.createform.CreateFormActivity
import com.cpu.quikdata.feature.createform.CreateFormActivity.Companion.FORM_ID_KEY
import com.cpu.quikdata.feature.createform.CreateFormAltViewModel
import com.cpu.quikdata.feature.createform.CreateFormRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap

/**
 * Provides shared dependencies of CreateFormActivity and its children
 */
@Module
abstract class CreateFormSharedModule {

    @Binds
    abstract fun bindsCreateFormActivity(activity: CreateFormActivity): Activity

    @Binds
    @IntoMap
    @CreateFormActivityScope
    @ViewModelKey(CreateFormAltViewModel::class)
    abstract fun bindsCreateFormViewModel(createFormViewModel: CreateFormAltViewModel): ViewModel

    @Module
    companion object {

        @Provides
        @CreateFormActivityScope
        fun provideBundle(activity: Activity): Bundle = activity.intent.extras ?: Bundle.EMPTY

        @Provides
        @CreateFormActivityScope
        fun provideFormId(bundle: Bundle): String = bundle.getString(FORM_ID_KEY, "")

        @Provides
        @CreateFormActivityScope
        fun provideCreateFormRepository(application: Application, formId: String)
                : CreateFormRepository {
            return CreateFormRepository(application, formId)
        }

    }
}