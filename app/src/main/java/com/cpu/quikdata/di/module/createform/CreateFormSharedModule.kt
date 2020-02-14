package com.cpu.quikdata.di.module.createform

import android.app.Activity
import android.os.Bundle
import androidx.lifecycle.ViewModel
import com.cpu.quikdata.data.AppDatabase
import com.cpu.quikdata.di.annotation.ActivityScope
import com.cpu.quikdata.di.annotation.FormBundleQualifier
import com.cpu.quikdata.di.annotation.FormIdQualifier
import com.cpu.quikdata.di.annotation.ViewModelKey
import com.cpu.quikdata.di.module.viewmodel.AssistedInjectModule
import com.cpu.quikdata.feature.createform.CreateFormActivity
import com.cpu.quikdata.feature.createform.CreateFormActivity.Companion.FORM_ID_KEY
import com.cpu.quikdata.feature.createform.CreateFormRepository
import com.cpu.quikdata.feature.createform.CreateFormViewModel
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap

/**
 * Provides shared dependencies of CreateFormActivity and its children
 */

@Module(includes = [AssistedInjectModule::class])
abstract class CreateFormSharedModule {

    @Binds
    @ActivityScope
    abstract fun bindsCreateFormActivity(activity: CreateFormActivity): Activity

    @Binds
    @IntoMap
    @ActivityScope
    @ViewModelKey(CreateFormViewModel::class)
    abstract fun bindsCreateFormViewModel(createFormViewModel: CreateFormViewModel): ViewModel

    companion object {

        @Provides
        @FormBundleQualifier
        @ActivityScope
        fun provideBundle(activity: Activity): Bundle = activity.intent.extras ?: Bundle.EMPTY

        @Provides
        @FormIdQualifier
        @ActivityScope
        fun provideFormId(@FormBundleQualifier bundle: Bundle): String = bundle.getString(FORM_ID_KEY, "")

        @Provides
        @ActivityScope
        fun provideCreateFormRepository(database: AppDatabase, @FormIdQualifier formId: String)
                : CreateFormRepository {
            return CreateFormRepository(database, formId)
        }

    }
}
