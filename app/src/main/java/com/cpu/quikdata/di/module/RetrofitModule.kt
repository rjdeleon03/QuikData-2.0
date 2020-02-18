package com.cpu.quikdata.di.module

import com.cpu.quikdata.FIREBASE_MESSAGING_SERVER_KEY
import com.cpu.quikdata.di.annotation.FirebaseMessagingApiQualifier
import com.cpu.quikdata.network.FirebaseMessagingApi
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import dagger.Module
import dagger.Provides
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
class RetrofitModule {

    companion object {
        private const val BASE_URL_FIREBASE_MESSAGING = "https://fcm.googleapis.com/fcm/"
    }

    @Provides
    fun provideEmergencyInterceptor(): Interceptor {

        return Interceptor {chain ->
            val newUrl = chain.request().url()
                .newBuilder()
                .build()

            val newRequest = chain.request()
                .newBuilder()
                .url(newUrl)
                .header("Authorization", "key=$FIREBASE_MESSAGING_SERVER_KEY")
                .build()

            chain.proceed(newRequest)
        }
    }

    @Provides
    fun provideRetrofitInstance(interceptor: Interceptor): Retrofit {

        val logging = HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BODY }

        val client = OkHttpClient().newBuilder()
            .addInterceptor(interceptor)
            .addInterceptor(logging)
            .build()

        return Retrofit.Builder()
            .client(client)
            .baseUrl(BASE_URL_FIREBASE_MESSAGING)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .build()
    }

    @Provides
    @FirebaseMessagingApiQualifier
    fun provideFirebaseMessagingApi(retrofit: Retrofit): FirebaseMessagingApi {
        return retrofit.create(FirebaseMessagingApi::class.java)
    }

}