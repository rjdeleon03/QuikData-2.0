package com.cpu.quikdata.feature.createform.livelihoodsinfo.estimateddamage

import androidx.lifecycle.LiveData
import com.cpu.quikdata.base.BaseUpdateableRepository
import com.cpu.quikdata.data.AppDatabase
import com.cpu.quikdata.data.livelihoodsinfo.estimateddamage.EstimatedDamageComplete
import com.cpu.quikdata.utils.runOnIoThread

class EstimatedDamageRepository(private val mDatabase: AppDatabase, val formId: String) :
    BaseUpdateableRepository<EstimatedDamageComplete>() {

    private val mEstimatedDamage = mDatabase.estimatedDamageRowDao().getByFormId(formId)

    val estimatedDamage: LiveData<List<EstimatedDamageComplete>>
        get() = mEstimatedDamage

    override fun updateData(data: EstimatedDamageComplete) {
        runOnIoThread {
            mDatabase.estimatedDamageRowDao().update(data.row!!)
            mDatabase.estimatedDamageTypeDao().update(data.types!!)
        }
    }
}