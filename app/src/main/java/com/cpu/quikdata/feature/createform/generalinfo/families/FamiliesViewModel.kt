package com.cpu.quikdata.feature.createform.generalinfo.families

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.cpu.quikdata.data.generalinfo.families.Families
import javax.inject.Inject

class FamiliesViewModel @Inject constructor (private val mRepository: FamiliesRepository)
    : ViewModel() {

    val families: LiveData<Families>
        get() = mRepository.families

    fun updateFamilies(families: Families) = mRepository.updateData(families)
}
