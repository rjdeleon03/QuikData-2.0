package com.cpu.quikdata.di.module.createform.sections

import androidx.lifecycle.ViewModel
import com.cpu.quikdata.data.AppDatabase
import com.cpu.quikdata.di.annotation.FormIdQualifier
import com.cpu.quikdata.di.annotation.ViewModelKey
import com.cpu.quikdata.feature.createform.generalinfo.calamityinfo.CalamityInfoRepository
import com.cpu.quikdata.feature.createform.generalinfo.calamityinfo.CalamityInfoViewModel
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap

@Module
abstract class CalamityInfoModule {

    @Binds
    @IntoMap
    @ViewModelKey(CalamityInfoViewModel::class)
    abstract fun bindsCalamityInfoViewModel(calamityInfoViewModel: CalamityInfoViewModel): ViewModel

    companion object {

        @Provides
        fun provideCalamityInfoRepository(database: AppDatabase, @FormIdQualifier formId: String)
                : CalamityInfoRepository {
            return CalamityInfoRepository(database, formId)
        }
    }
}