package com.cpu.quikdata.di

import android.app.Application
import android.content.Context
import com.cpu.quikdata.di.module.FirebaseModule
import com.cpu.quikdata.di.module.SharedPrefsModule
import com.cpu.quikdata.feature.QuikDataApp
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [SharedPrefsModule::class, FirebaseModule::class]
)
interface AppComponent {

    @Component.Factory
    interface Factory {

        fun create(@BindsInstance applicationContext: Context): AppComponent
    }

    fun inject(application: QuikDataApp)
}