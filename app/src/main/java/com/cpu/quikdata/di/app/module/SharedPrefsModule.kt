package com.cpu.quikdata.di.app.module

import android.app.Application
import com.cpu.quikdata.common.helper.SharedPreferencesHelper
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class SharedPrefsModule {

    @Provides
    @Singleton
    fun provideSharedPrefsHelper(application: Application): SharedPreferencesHelper {
        return SharedPreferencesHelper(application)
    }

//    @Provides
//    @Singleton
//    fun provideSharedPrefsHelper(context: Context): SharedPreferencesHelper {
//        return SharedPreferencesHelper(context)
//    }
}