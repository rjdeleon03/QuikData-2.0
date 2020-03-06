package com.cpu.quikdata.feature.createform.livelihoodsinfo.estimateddamage

import androidx.lifecycle.LiveData
import com.cpu.quikdata.data.AppDatabase
import com.cpu.quikdata.data.livelihoodsinfo.estimateddamage.EstimatedDamageComplete
import com.cpu.quikdata.utils.runOnIoThread
import javax.inject.Inject

class EstimatedDamageRepository @Inject constructor(
    private val mDatabase: AppDatabase, formId: String) {

    private val mEstimatedDamage = mDatabase.estimatedDamageRowDao().getByFormId(formId)

    val estimatedDamage: LiveData<List<EstimatedDamageComplete>>
        get() = mEstimatedDamage

    fun updateData(data: EstimatedDamageComplete) {
        runOnIoThread {
            mDatabase.estimatedDamageRowDao().update(data.row!!)
            mDatabase.estimatedDamageTypeDao().update(data.types!!)
        }
    }
}