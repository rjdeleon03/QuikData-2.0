package com.cpu.quikdata.di.module.createform

import android.app.Activity
import android.os.Bundle
import com.cpu.quikdata.di.annotation.CreateFormActivityScope
import com.cpu.quikdata.feature.createform.CreateFormActivity
import com.cpu.quikdata.feature.createform.CreateFormActivity.Companion.FORM_ID_KEY
import dagger.Binds
import dagger.Module
import dagger.Provides

/**
 * Provides activities' dependencies
 */
@Module
abstract class CreateFormActivityModule {

    @Binds
    abstract fun bindsCreateFormActivity(activity: CreateFormActivity): Activity

    @Module
    companion object {

        @Provides
        @CreateFormActivityScope
        fun provideBundle(activity: Activity): Bundle = activity.intent.extras ?: Bundle.EMPTY

        @Provides
        @CreateFormActivityScope
        fun provideFormId(bundle: Bundle): String = bundle.getString(FORM_ID_KEY, "")

    }
}
