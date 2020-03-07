package com.cpu.quikdata.feature.createform.shelterinfo.housedamage

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.cpu.quikdata.common.runOnIoThread
import com.cpu.quikdata.data.shelterinfo.housedamagerow.HouseDamageRow
import javax.inject.Inject


class HouseDamageViewModel @Inject constructor(private val mRepository: HouseDamageRepository) :
    ViewModel() {

    val houseDamage: LiveData<List<HouseDamageRow>>
        get() = mRepository.houseDamage

    fun updateRow(houseDamageRow: HouseDamageRow) =
        runOnIoThread { mRepository.updateData(houseDamageRow) }
}
