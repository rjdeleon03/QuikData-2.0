package com.cpu.quikdata.feature.createform.shelterinfo.housedamage

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.cpu.quikdata.data.shelterinfo.housedamagerow.HouseDamageRow


class HouseDamageViewModel(application: Application, formId: String) :
    AndroidViewModel(application) {

    private val mRepository = HouseDamageRepository(application, formId)

    val houseDamage: LiveData<List<HouseDamageRow>>
        get() = mRepository.houseDamage

    fun updateRow(houseDamageRow: HouseDamageRow) = mRepository.updateData(houseDamageRow)
}
