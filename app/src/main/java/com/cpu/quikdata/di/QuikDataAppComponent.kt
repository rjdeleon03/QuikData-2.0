package com.cpu.quikdata.di

import android.app.Application
import com.cpu.quikdata.feature.QuikDataApp
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule

@Component(modules = [AndroidSupportInjectionModule::class])
interface QuikDataAppComponent: AndroidInjector<QuikDataApp> {

    @Component.Builder
    interface Builder {

        @BindsInstance
        // ^Used when object already exists upon creation of component
        fun application(application: Application): Builder

        fun build(): QuikDataAppComponent
    }

}