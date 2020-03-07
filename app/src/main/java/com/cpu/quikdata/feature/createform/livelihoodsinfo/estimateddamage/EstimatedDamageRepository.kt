package com.cpu.quikdata.feature.createform.livelihoodsinfo.estimateddamage

import androidx.lifecycle.LiveData
import com.cpu.quikdata.data.AppDatabase
import com.cpu.quikdata.data.livelihoodsinfo.estimateddamage.EstimatedDamageComplete
import javax.inject.Inject

class EstimatedDamageRepository @Inject constructor(
    private val mDatabase: AppDatabase, formId: String
) {

    private val mEstimatedDamage = mDatabase.estimatedDamageRowDao().getByFormId(formId)

    val estimatedDamage: LiveData<List<EstimatedDamageComplete>>
        get() = mEstimatedDamage

    suspend fun updateData(data: EstimatedDamageComplete) {
        data.row?.let {
            mDatabase.estimatedDamageRowDao().update(it)
        }
        data.types?.let {
            mDatabase.estimatedDamageTypeDao().update(it)
        }
    }
}