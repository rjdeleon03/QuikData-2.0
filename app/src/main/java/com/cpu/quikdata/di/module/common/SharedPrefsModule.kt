package com.cpu.quikdata.di.module.common

import android.app.Application
import com.cpu.quikdata.helpers.SharedPreferencesHelper
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class SharedPrefsModule {

    companion object{

        @Provides
        fun provideSharedPrefsHelper(application: Application): SharedPreferencesHelper {
            return SharedPreferencesHelper(application)
        }

    }
}