package com.cpu.quikdata.feature.createform.livelihoodsinfo.estimateddamage

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.cpu.quikdata.common.runOnIoThread
import com.cpu.quikdata.data.livelihoodsinfo.estimateddamage.EstimatedDamageComplete
import javax.inject.Inject

class EstimatedDamageViewModel @Inject constructor(
    private val mRepository: EstimatedDamageRepository
) : ViewModel() {

    val estimatedDamage: LiveData<List<EstimatedDamageComplete>>
        get() = mRepository.estimatedDamage

    fun updateRow(estimatedDamageComplete: EstimatedDamageComplete) =
        runOnIoThread { mRepository.updateData(estimatedDamageComplete) }
}
