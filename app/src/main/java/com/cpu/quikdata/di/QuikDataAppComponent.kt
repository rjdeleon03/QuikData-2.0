package com.cpu.quikdata.di

import com.cpu.quikdata.feature.QuikDataApp
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [SharedPrefsModule::class])
interface QuikDataAppComponent {

    fun inject(quikDataApp: QuikDataApp)
}