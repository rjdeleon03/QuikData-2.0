package com.cpu.quikdata.di.component.repository

import com.cpu.quikdata.di.module.DatabaseModule
import com.cpu.quikdata.feature.createform.generalinfo.infrastructuredamage.InfrastructureDamageRepository
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [DatabaseModule::class])
interface InfrastructureDamageRepositoryComponent {

    fun inject(infrastructureDamageRepository: InfrastructureDamageRepository)
}