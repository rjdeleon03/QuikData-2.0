package com.cpu.quikdata.feature.createform.generalinfo.families

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.cpu.quikdata.data.generalinfo.families.Families

class FamiliesViewModel(application: Application, formId: String) : AndroidViewModel(application) {

    private val mRepository = FamiliesRepository(application, formId)

    val families: LiveData<Families>
        get() = mRepository.families

    fun updateFamilies(families: Families) = mRepository.updateData(families)
}
