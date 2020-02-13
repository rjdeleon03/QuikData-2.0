package com.cpu.quikdata.di.module.createform

import android.app.Activity
import android.os.Bundle
import androidx.lifecycle.ViewModel
import com.cpu.quikdata.data.AppDatabase
import com.cpu.quikdata.di.annotation.CreateFormActivityScope
import com.cpu.quikdata.di.annotation.FormBundleQualifier
import com.cpu.quikdata.di.annotation.FormIdQualifier
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
    @CreateFormActivityScope
    abstract fun bindsCreateFormActivity(activity: CreateFormActivity): Activity

    @Binds
    @IntoMap
    @CreateFormActivityScope
    @ViewModelKey(CreateFormAltViewModel::class)
    abstract fun bindsCreateFormViewModel(createFormViewModel: CreateFormAltViewModel): ViewModel

    companion object {

        @Provides
        @FormBundleQualifier
        @CreateFormActivityScope
        fun provideBundle(activity: Activity): Bundle = activity.intent.extras ?: Bundle.EMPTY

        @Provides
        @FormIdQualifier
        @CreateFormActivityScope
        fun provideFormId(@FormBundleQualifier bundle: Bundle): String = bundle.getString(FORM_ID_KEY, "")

        @Provides
        @CreateFormActivityScope
        fun provideCreateFormRepository(database: AppDatabase, @FormIdQualifier formId: String)
                : CreateFormRepository {
            return CreateFormRepository(database, formId)
        }

    }
}
