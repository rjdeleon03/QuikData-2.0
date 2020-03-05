package com.cpu.quikdata.di.app.module

import android.app.Application
import com.cpu.quikdata.data.AppDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DatabaseModule {

    @Provides
    @Singleton
    fun provideAppDatabase(application: Application): AppDatabase {
        return AppDatabase.create(application)
    }
}