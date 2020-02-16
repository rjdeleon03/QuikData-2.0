package com.cpu.quikdata.di.module

import android.app.Application
import androidx.room.Room
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestManager
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.cpu.quikdata.common.FirebaseHelper
import com.cpu.quikdata.data.AppDatabase
import com.cpu.quikdata.helpers.SharedPreferencesHelper
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.iid.FirebaseInstanceId
import com.google.firebase.storage.FirebaseStorage
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Provides all app-level dependencies
 */
@Module
class AppModule {

    companion object {

        /* Room */
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

        /* Shared Prefs */
        @Singleton
        @Provides
        fun provideSharedPreferencesModule(application: Application): SharedPreferencesHelper {
            return SharedPreferencesHelper(application)
        }

        /* Glide */
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

        /* Firebase */

        @Singleton
        @Provides
        fun provideFirebaseDatabase(): FirebaseDatabase {
            return FirebaseDatabase.getInstance()
        }

        @Singleton
        @Provides
        fun provideFirestore(): FirebaseFirestore {
            return FirebaseFirestore.getInstance()
        }

        @Singleton
        @Provides
        fun provideStorage(): FirebaseStorage {
            return FirebaseStorage.getInstance()
        }

        @Singleton
        @Provides
        fun provideInstanceId(): FirebaseInstanceId {
            return FirebaseInstanceId.getInstance()
        }

        @Provides
        fun provideFirebaseHelper(database: AppDatabase,
                                  firestore: FirebaseFirestore,
                                  storage: FirebaseStorage
        )
                : FirebaseHelper {
            return FirebaseHelper(database, firestore, storage)
        }
    }
}