package com.cpu.quikdata.di.module

import android.content.Context
import com.cpu.quikdata.common.SharedPreferencesHelper
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class SharedPrefsModule {

    @Provides
    @Singleton
    fun provideSharedPrefsHelper(context: Context): SharedPreferencesHelper {
        return SharedPreferencesHelper(context.applicationContext)
    }
}