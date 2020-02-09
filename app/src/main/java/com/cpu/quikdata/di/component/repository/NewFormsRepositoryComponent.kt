package com.cpu.quikdata.di.component.repository

import com.cpu.quikdata.di.module.DatabaseModule
import com.cpu.quikdata.di.module.FirebaseHelperModule
import com.cpu.quikdata.feature.main.newforms.NewFormsRepository
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [
    DatabaseModule::class,
    FirebaseHelperModule::class])
interface NewFormsRepositoryComponent {

    fun inject (newFormsRepository: NewFormsRepository)
}