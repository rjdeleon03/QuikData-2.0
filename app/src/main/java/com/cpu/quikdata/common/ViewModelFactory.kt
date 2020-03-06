package com.cpu.quikdata.common

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.cpu.quikdata.feature.createform.evacuationinfo.EvacuationInfoViewModel
import com.cpu.quikdata.feature.createform.evacuationinfo.evacuationage.EvacuationAgeViewModel
import com.cpu.quikdata.feature.createform.evacuationinfo.evacuationcoping.EvacuationCopingViewModel
import com.cpu.quikdata.feature.createform.evacuationinfo.evacuationfacilities.EvacuationFacilitiesViewModel
import com.cpu.quikdata.feature.createform.evacuationinfo.evacuationprotection.EvacuationProtectionViewModel
import com.cpu.quikdata.feature.createform.evacuationinfo.evacuationwash.EvacuationWashViewModel
import com.cpu.quikdata.feature.createform.evacuationinfo.siteinfo.SiteInfoViewModel

@Suppress("UNCHECKED_CAST")
class ViewModelFactory(application: Application, formId: String) :
    ViewModelProvider.AndroidViewModelFactory(application) {

    private val mApplication = application
    private val mFormId = formId

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {

        return when (modelClass) {
            EvacuationInfoViewModel::class.java -> EvacuationInfoViewModel(mApplication, mFormId) as T
            SiteInfoViewModel::class.java -> SiteInfoViewModel(mApplication, mFormId) as T
            EvacuationAgeViewModel::class.java -> EvacuationAgeViewModel(mApplication, mFormId) as T
            EvacuationFacilitiesViewModel::class.java -> EvacuationFacilitiesViewModel(mApplication, mFormId) as T
            EvacuationWashViewModel::class.java -> EvacuationWashViewModel(mApplication, mFormId) as T
            EvacuationProtectionViewModel::class.java -> EvacuationProtectionViewModel(mApplication, mFormId) as T
            EvacuationCopingViewModel::class.java -> EvacuationCopingViewModel(mApplication, mFormId) as T

            else -> EvacuationCopingViewModel(mApplication, mFormId) as T
        }
    }
}