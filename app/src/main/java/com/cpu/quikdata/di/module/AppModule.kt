package com.cpu.quikdata.di.module

import android.app.Application
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestManager
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.cpu.quikdata.helpers.SharedPreferencesHelper
import dagger.Module
import dagger.Provides

@Module
class AppModule {

    companion object {

        @Provides
        fun provideSharedPreferencesModule(application: Application): SharedPreferencesHelper {
            return SharedPreferencesHelper(application)
        }

        @Provides
        fun provideRequestOptions(): RequestOptions {
            return RequestOptions
                .diskCacheStrategyOf(DiskCacheStrategy.ALL)
        }

        @Provides
        fun provideGlideInstance(application: Application, requestOptions: RequestOptions)
                : RequestManager {
            return Glide.with(application)
                .setDefaultRequestOptions(requestOptions)
        }
    }
}