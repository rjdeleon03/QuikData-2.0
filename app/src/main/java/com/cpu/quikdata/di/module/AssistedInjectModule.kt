package com.cpu.quikdata.di.module

import android.app.Activity
import android.content.Context
import com.cpu.quikdata.di.annotation.CreateFormActivityScope
import com.squareup.inject.assisted.dagger2.AssistedModule
import dagger.Module
import dagger.Provides

@AssistedModule
@Module(includes = [AssistedInject_AssistedInjectModule::class])
abstract class AssistedInjectModule {

    companion object {

        @Provides
        @CreateFormActivityScope
        fun provideContextFromActivity(activity: Activity): Context = activity
    }
}