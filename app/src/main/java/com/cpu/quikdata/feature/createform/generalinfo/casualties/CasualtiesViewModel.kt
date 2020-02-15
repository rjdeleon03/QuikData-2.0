package com.cpu.quikdata.feature.createform.generalinfo.casualties

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.cpu.quikdata.data.generalinfo.casualtiesrow.CasualtiesRow
import javax.inject.Inject

class CasualtiesViewModel @Inject constructor (private val mRepository: CasualtiesRepository)
    : ViewModel() {

    val casualties: LiveData<List<CasualtiesRow>>
        get() = mRepository.casualties

    fun updateRow(casualties: CasualtiesRow) = mRepository.updateData(casualties)

}
