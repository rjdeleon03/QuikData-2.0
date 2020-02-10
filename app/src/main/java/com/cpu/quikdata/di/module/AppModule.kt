package com.cpu.quikdata.di.module

import android.app.Application
import com.cpu.quikdata.helpers.SharedPreferencesHelper
import dagger.Module
import dagger.Provides

@Module
class AppModule {

    @Module
    companion object {

        @JvmStatic
        @Provides
        fun someString(): String {
            return "----- Test String! -----"
        }

        @JvmStatic
        @Provides
        fun provideSharedPreferencesModule(application: Application): SharedPreferencesHelper {
            return SharedPreferencesHelper(application)
        }
    }
}