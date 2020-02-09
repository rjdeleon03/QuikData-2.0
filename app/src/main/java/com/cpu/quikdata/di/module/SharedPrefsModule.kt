package com.cpu.quikdata.di.module

import com.cpu.quikdata.feature.QuikDataApp
import com.cpu.quikdata.helpers.SharedPreferencesHelper
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class SharedPrefsModule {

    @Provides
    @Singleton
    fun provideSharedPrefsHelper(application: QuikDataApp): SharedPreferencesHelper {
        return SharedPreferencesHelper(application)
    }
}