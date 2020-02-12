package com.cpu.quikdata.di.module.view

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
abstract class ActivityModule {

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

    /*



    @Binds
    abstract fun bindViewModelFactory(factory: MyViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel::class)
    abstract fun mainViewModel(mainViewModel: MainViewModel): ViewModel

    @Module
    companion object {

        @JvmStatic
        @Provides
        fun bundle(activity: Activity): Bundle = activity.intent.extras ?: Bundle.EMPTY

        @JvmStatic
        @Provides
        fun someParam(bundle: Bundle): String = bundle.getString("test", "abc")

    }
     */

}
