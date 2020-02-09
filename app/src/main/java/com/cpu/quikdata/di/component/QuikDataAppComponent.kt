package com.cpu.quikdata.di.component

import com.cpu.quikdata.di.module.AppModule
import com.cpu.quikdata.di.module.DatabaseModule
import com.cpu.quikdata.di.module.SharedPrefsModule
import com.cpu.quikdata.feature.QuikDataApp
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [
    SharedPrefsModule::class,
    AppModule::class,
    DatabaseModule::class])
interface QuikDataAppComponent {

    fun inject(application: QuikDataApp)
}