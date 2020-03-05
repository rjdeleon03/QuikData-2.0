package com.cpu.quikdata.di

import android.app.Application
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component
interface AppComponent {

    @Component.Factory
    interface Factory {

        fun create(@BindsInstance application: Application): AppComponent
    }
}