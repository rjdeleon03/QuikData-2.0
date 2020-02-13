package com.cpu.quikdata.di.module.createform.sections

import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import com.cpu.quikdata.data.AppDatabase
import com.cpu.quikdata.di.annotation.EvacuationIdQualifier
import com.cpu.quikdata.di.annotation.ViewModelKey
import com.cpu.quikdata.feature.createform.evacuationinfo.siteinfo.SiteInfoFragment
import com.cpu.quikdata.feature.createform.evacuationinfo.siteinfo.SiteInfoRepository
import com.cpu.quikdata.feature.createform.evacuationinfo.siteinfo.SiteInfoViewModel
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap

@Module
abstract class EvacuationSiteInfoModule {

    @Binds
    abstract fun bindsSiteInfoFragment(fragment: SiteInfoFragment): Fragment

    @Binds
    @IntoMap
    @ViewModelKey(SiteInfoViewModel::class)
    abstract fun bindsSiteInfoViewModel(siteInfoViewModel: SiteInfoViewModel): ViewModel

    companion object {

        @Provides
        fun provideSiteInfoRepository(database: AppDatabase, @EvacuationIdQualifier evacuationId: String)
                : SiteInfoRepository {
            return SiteInfoRepository(database, evacuationId)
        }
    }
}