package com.cpu.quikdata.di.component

import android.app.Application
import com.cpu.quikdata.di.module.*
import com.cpu.quikdata.di.module.viewmodel.ViewModelFactoryModule
import com.cpu.quikdata.feature.app.QuikDataApp
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(modules = [
    AndroidSupportInjectionModule::class,
    AppModule::class,
    ActivityBuilderModule::class,
    ServiceBuilderModule::class,
    BroadcastReceiverBuilderModule::class,
    ViewModelFactoryModule::class,
    RetrofitModule::class])
interface AppComponent: AndroidInjector<QuikDataApp> {

    @Component.Builder
    interface Builder {

        @BindsInstance
        // ^Used when object already exists upon creation of component
        fun application(application: Application): Builder

        fun build(): AppComponent
    }

}