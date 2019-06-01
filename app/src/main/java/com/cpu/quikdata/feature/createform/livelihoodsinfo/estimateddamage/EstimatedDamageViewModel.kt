package com.cpu.quikdata.feature.createform.livelihoodsinfo.estimateddamage

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.cpu.quikdata.data.livelihoodsinfo.estimateddamage.EstimatedDamageComplete

class EstimatedDamageViewModel(application: Application, formId: String) : 
    AndroidViewModel(application) {

    private val mRepository = EstimatedDamageRepository(application, formId)

    val estimatedDamage: LiveData<List<EstimatedDamageComplete>>
        get() = mRepository.estimatedDamage

    fun updateRow(estimatedDamageComplete: EstimatedDamageComplete) =
        mRepository.updateData(estimatedDamageComplete)
}
