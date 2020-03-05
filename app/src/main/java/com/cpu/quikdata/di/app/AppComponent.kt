package com.cpu.quikdata.di.app

import android.app.Application
import com.cpu.quikdata.di.app.module.DatabaseModule
import com.cpu.quikdata.di.app.module.FirebaseModule
import com.cpu.quikdata.di.app.module.SharedPrefsModule
import com.cpu.quikdata.di.app.module.ViewModelModule
import com.cpu.quikdata.di.main.newforms.NewFormsComponent
import com.cpu.quikdata.di.main.prefilledinfo.PrefilledInfoComponent
import com.cpu.quikdata.feature.QuikDataApp
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        DatabaseModule::class,
        SharedPrefsModule::class,
        FirebaseModule::class,
        ViewModelModule::class,
        AppSubcomponents::class
    ]
)
interface AppComponent {

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance application: Application): AppComponent
    }

    fun prefilledInfoComponent(): PrefilledInfoComponent.Factory
    fun newFormsComponent(): NewFormsComponent.Factory

    fun inject(application: QuikDataApp)
}