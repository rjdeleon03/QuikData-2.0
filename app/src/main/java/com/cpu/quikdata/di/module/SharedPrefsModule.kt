package com.cpu.quikdata.di.module

import android.app.Application
import com.cpu.quikdata.helpers.SharedPreferencesHelper
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class SharedPrefsModule {

    companion object{

        @Provides
        @Singleton
        fun provideSharedPrefsHelper(application: Application): SharedPreferencesHelper {
            return SharedPreferencesHelper(application)
        }

    }
}