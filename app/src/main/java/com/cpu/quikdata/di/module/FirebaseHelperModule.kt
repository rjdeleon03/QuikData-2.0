package com.cpu.quikdata.di.module

import com.cpu.quikdata.common.FirebaseHelper
import dagger.Module
import dagger.Provides

@Module
class FirebaseHelperModule {

    @Provides
    fun provideFirebaseHelper() = FirebaseHelper()
}