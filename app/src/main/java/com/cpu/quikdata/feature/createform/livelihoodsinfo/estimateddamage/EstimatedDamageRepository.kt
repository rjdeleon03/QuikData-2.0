package com.cpu.quikdata.feature.createform.livelihoodsinfo.estimateddamage

import androidx.lifecycle.LiveData
import com.cpu.quikdata.base.BaseUpdateableRepository
import com.cpu.quikdata.data.AppDatabase
import com.cpu.quikdata.data.livelihoodsinfo.estimateddamage.EstimatedDamageComplete

class EstimatedDamageRepository(private val mDatabase: AppDatabase, val formId: String) :
    BaseUpdateableRepository<EstimatedDamageComplete>() {

    private val mEstimatedDamage = mDatabase.estimatedDamageRowDao().getByFormId(formId)

    val estimatedDamage: LiveData<List<EstimatedDamageComplete>>
        get() = mEstimatedDamage

    override suspend fun updateData(data: EstimatedDamageComplete) {
        mDatabase.estimatedDamageRowDao().update(data.row!!)
        mDatabase.estimatedDamageTypeDao().update(data.types!!)
    }
}