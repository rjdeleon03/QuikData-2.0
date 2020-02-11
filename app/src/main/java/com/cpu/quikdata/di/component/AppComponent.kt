package com.cpu.quikdata.di.component

import android.app.Application
import com.cpu.quikdata.di.module.ActivityBuilderModule
import com.cpu.quikdata.di.module.AppModule
import com.cpu.quikdata.di.module.FragmentBuilderModule
import com.cpu.quikdata.di.module.ViewModelFactoryModule
import com.cpu.quikdata.di.module.common.FirebaseModule
import com.cpu.quikdata.feature.QuikDataApp
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
    FragmentBuilderModule::class,
    ViewModelFactoryModule::class,
    FirebaseModule::class])
interface AppComponent: AndroidInjector<QuikDataApp> {

    @Component.Builder
    interface Builder {

        @BindsInstance
        // ^Used when object already exists upon creation of component
        fun application(application: Application): Builder

        fun build(): AppComponent
    }

}