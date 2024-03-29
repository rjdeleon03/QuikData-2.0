package com.cpu.quikdata.feature.createform.generalinfo.casualties

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.cpu.quikdata.data.generalinfo.casualtiesrow.CasualtiesRow

class CasualtiesViewModel(application: Application, formId: String) : AndroidViewModel(application) {

    private val mRepository = CasualtiesRepository(application, formId)

    val casualties: LiveData<List<CasualtiesRow>>
        get() = mRepository.casualties

    fun updateRow(casualties: CasualtiesRow) = mRepository.updateData(casualties)

}
