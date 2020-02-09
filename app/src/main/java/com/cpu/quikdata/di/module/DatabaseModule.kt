package com.cpu.quikdata.di.module

import androidx.room.Room
import com.cpu.quikdata.data.AppDatabase
import com.cpu.quikdata.feature.QuikDataApp
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DatabaseModule(val application: QuikDataApp) {

    private val mDatabase: AppDatabase = Room.databaseBuilder(
        application,
        AppDatabase::class.java,
        AppDatabase.DATABASE_NAME)
        .fallbackToDestructiveMigration()
        .build()

    @Singleton
    @Provides
    fun provideAppDatabase() = mDatabase

    @Singleton
    @Provides
    fun providePrefilledDataDao() = mDatabase.prefilledDataDao()

    @Singleton
    @Provides
    fun provideFormDao() = mDatabase.formDao()

    @Singleton
    @Provides
    fun provideCaseStoriesDao() = mDatabase.caseStoriesDao()
}