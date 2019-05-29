package com.cpu.quikdata.common

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.cpu.quikdata.feature.createform.formdetails.FormDetailsViewModel
import com.cpu.quikdata.feature.createform.generalinfo.calamityinfo.CalamityInfoViewModel
import com.cpu.quikdata.feature.createform.generalinfo.casualties.CasualtiesViewModel
import com.cpu.quikdata.feature.createform.generalinfo.causeofdeath.CauseOfDeathViewModel
import com.cpu.quikdata.feature.createform.generalinfo.families.FamiliesViewModel
import com.cpu.quikdata.feature.createform.generalinfo.population.PopulationViewModel
import com.cpu.quikdata.feature.createform.generalinfo.vulnerable.VulnerableViewModel

@Suppress("UNCHECKED_CAST")
class ViewModelFactory(application: Application, formId: String) :
    ViewModelProvider.AndroidViewModelFactory(application) {

    private val mApplication = application
    private val mFormId = formId

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {

        return when (modelClass) {
            CalamityInfoViewModel::class.java -> CalamityInfoViewModel(mApplication, mFormId) as T
            PopulationViewModel::class.java -> PopulationViewModel(mApplication, mFormId) as T
            FamiliesViewModel::class.java -> FamiliesViewModel(mApplication, mFormId) as T
            VulnerableViewModel::class.java -> VulnerableViewModel(mApplication, mFormId) as T
            CasualtiesViewModel::class.java -> CasualtiesViewModel(mApplication, mFormId) as T
            CauseOfDeathViewModel::class.java -> CauseOfDeathViewModel(mApplication, mFormId) as T
            else -> FormDetailsViewModel(mApplication, mFormId) as T
        }
    }
}