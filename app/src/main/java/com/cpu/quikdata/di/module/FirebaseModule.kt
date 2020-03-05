package com.cpu.quikdata.di.module

import com.cpu.quikdata.common.FirebaseHelper
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class FirebaseModule {

    @Provides
    @Singleton
    fun provideFirebaseHelper(firestore: FirebaseFirestore,
                              storage: FirebaseStorage): FirebaseHelper {
//        return FirebaseHelper(firestore, storage)
        return FirebaseHelper()
    }

    @Provides
    @Singleton
    fun provideFirebaseFirestore(): FirebaseFirestore {
        return FirebaseFirestore.getInstance()
    }

    @Provides
    @Singleton
    fun provideFirebaseStorage(): FirebaseStorage {
        return FirebaseStorage.getInstance()
    }
}