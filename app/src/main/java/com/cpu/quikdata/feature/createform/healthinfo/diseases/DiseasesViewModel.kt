package com.cpu.quikdata.feature.createform.healthinfo.diseases

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.cpu.quikdata.data.health.diseasesrow.DiseasesRow
import javax.inject.Inject

class DiseasesViewModel @Inject constructor(private val mRepository: DiseasesRepository)
    : ViewModel() {

    val diseases: LiveData<List<DiseasesRow>>
        get() = mRepository.diseases

    fun updateRow(diseasesRow: DiseasesRow) = mRepository.updateData(diseasesRow)
}
