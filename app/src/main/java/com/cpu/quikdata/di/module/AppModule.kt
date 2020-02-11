package com.cpu.quikdata.di.module

import android.app.Application
import androidx.room.Room
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestManager
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.cpu.quikdata.data.AppDatabase
import com.cpu.quikdata.helpers.SharedPreferencesHelper
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule {

    companion object {

        @Singleton
        @Provides
        fun provideAppDatabase(application: Application): AppDatabase {
            return Room.databaseBuilder(
                application,
                AppDatabase::class.java,
                AppDatabase.DATABASE_NAME)
                .fallbackToDestructiveMigration()
                .build()
        }

        @Singleton
        @Provides
        fun provideSharedPreferencesModule(application: Application): SharedPreferencesHelper {
            return SharedPreferencesHelper(application)
        }

        @Singleton
        @Provides
        fun provideRequestOptions(): RequestOptions {
            return RequestOptions
                .diskCacheStrategyOf(DiskCacheStrategy.ALL)
        }

        @Singleton
        @Provides
        fun provideGlideInstance(application: Application, requestOptions: RequestOptions)
                : RequestManager {
            return Glide.with(application)
                .setDefaultRequestOptions(requestOptions)
        }
    }
}