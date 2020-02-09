package com.cpu.quikdata.di.component.repository

import com.cpu.quikdata.di.module.DatabaseModule
import com.cpu.quikdata.feature.createform.formdetailsandbaseline.baselinedata.BaselineDataRepository
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [DatabaseModule::class])
interface BaselineDataRepositoryComponent {

    fun inject(baselineDataRepository: BaselineDataRepository)
}