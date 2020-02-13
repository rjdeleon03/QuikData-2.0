package com.cpu.quikdata.di.module.createform.sections

import android.app.Application
import androidx.lifecycle.ViewModel
import com.cpu.quikdata.di.annotation.FormIdQualifier
import com.cpu.quikdata.di.annotation.ViewModelKey
import com.cpu.quikdata.feature.createform.generalinfo.vulnerable.VulnerableRepository
import com.cpu.quikdata.feature.createform.generalinfo.vulnerable.VulnerableViewModel
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap

@Module
abstract class VulnerableModule {

    @Binds
    @IntoMap
    @ViewModelKey(VulnerableViewModel::class)
    abstract fun bindsVulnerableViewModel(vulnerableViewModel: VulnerableViewModel): ViewModel

    companion object {

        @Provides
        fun provideVulnerableRepository(application: Application, @FormIdQualifier formId: String)
                : VulnerableRepository {
            return VulnerableRepository(application, formId)
        }
    }
}