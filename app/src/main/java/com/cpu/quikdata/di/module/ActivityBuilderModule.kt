package com.cpu.quikdata.di.module

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.cpu.quikdata.di.annotation.CreateFormActivityScope
import com.cpu.quikdata.di.annotation.MainActivityScope
import com.cpu.quikdata.di.module.viewmodel.CreateFormViewModelModule
import com.cpu.quikdata.feature.createform.CreateFormActivity
import com.cpu.quikdata.feature.createform.CreateFormActivity.Companion.FORM_ID_KEY
import com.cpu.quikdata.feature.main.MainActivity
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuilderModule {

    @MainActivityScope
    @ContributesAndroidInjector
    abstract fun contributeMainActivity(): MainActivity

    @CreateFormActivityScope
    @ContributesAndroidInjector(
        modules = [CreateFormViewModelModule::class]
    )
    abstract fun contributeCreateFormActivity(): CreateFormActivity

    @Binds
    @CreateFormActivityScope
    abstract fun bindsCreateFormActivity(activity: CreateFormActivity): AppCompatActivity

    companion object {

        @Provides
        fun provideCreateFormActivityBundle(activity: AppCompatActivity): Bundle {
            return activity.intent.extras ?: Bundle.EMPTY
        }

        @Provides
        fun provideCreateFormActivityFormId(bundle: Bundle): String {
            return bundle.getString(FORM_ID_KEY, "")
        }
    }
}