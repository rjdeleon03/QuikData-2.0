package com.cpu.quikdata.di.module.createform

import android.app.Activity
import android.content.Context
import android.os.Bundle
import androidx.lifecycle.ViewModel
import com.cpu.quikdata.data.AppDatabase
import com.cpu.quikdata.di.annotation.CreateFormActivityScope
import com.cpu.quikdata.di.annotation.FormBundleQualifier
import com.cpu.quikdata.di.annotation.FormIdQualifier
import com.cpu.quikdata.di.annotation.ViewModelKey
import com.cpu.quikdata.feature.createform.CreateFormActivity
import com.cpu.quikdata.feature.createform.CreateFormActivity.Companion.FORM_ID_KEY
import com.cpu.quikdata.feature.createform.CreateFormViewModel
import com.cpu.quikdata.feature.createform.CreateFormRepository
import com.squareup.inject.assisted.dagger2.AssistedModule
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap

/**
 * Provides shared dependencies of CreateFormActivity and its children
 */

@AssistedModule
@Module(includes = [AssistedInject_CreateFormSharedModule::class])

abstract class CreateFormSharedModule {

    @Binds
    @CreateFormActivityScope
    abstract fun bindsCreateFormActivity(activity: CreateFormActivity): Activity

    @Binds
    @IntoMap
    @CreateFormActivityScope
    @ViewModelKey(CreateFormViewModel::class)
    abstract fun bindsCreateFormViewModel(createFormViewModel: CreateFormViewModel): ViewModel

    companion object {

        @Provides
        @CreateFormActivityScope
        fun provideCreateFormContext(activity: Activity): Context = activity

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
