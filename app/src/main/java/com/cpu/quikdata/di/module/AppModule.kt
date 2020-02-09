package com.cpu.quikdata.di.module

import com.cpu.quikdata.feature.QuikDataApp
import dagger.Module
import dagger.Provides

@Module
class AppModule(private val mApplication: QuikDataApp) {

    @Provides
    fun provideApplication(): QuikDataApp = mApplication
}