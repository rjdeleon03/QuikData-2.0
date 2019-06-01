package com.cpu.quikdata.feature.createform.healthinfo.diseases

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.cpu.quikdata.data.health.diseasesrow.DiseasesRow

class DiseasesViewModel(application: Application, formId: String) :
    AndroidViewModel(application) {

    private val mRepository = DiseasesRepository(application, formId)

    val diseases: LiveData<List<DiseasesRow>>
        get() = mRepository.diseases

    fun updateRow(diseasesRow: DiseasesRow) = mRepository.updateData(diseasesRow)
}
