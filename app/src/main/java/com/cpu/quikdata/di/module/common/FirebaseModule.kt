package com.cpu.quikdata.di.module.common

import com.cpu.quikdata.common.FirebaseHelper
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import dagger.Module
import dagger.Provides

@Module
class FirebaseModule {

    companion object {

        @Provides
        fun provideFirestore(): FirebaseFirestore {
            return FirebaseFirestore.getInstance()
        }

        @Provides
        fun provideStorage(): FirebaseStorage {
            return FirebaseStorage.getInstance()
        }

        @Provides
        fun provideFirebaseHelper(firestore: FirebaseFirestore,
                                  storage: FirebaseStorage)
                : FirebaseHelper {
            return FirebaseHelper(firestore, storage)
        }
    }
}