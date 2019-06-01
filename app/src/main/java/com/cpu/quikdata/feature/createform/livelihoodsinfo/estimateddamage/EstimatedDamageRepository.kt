package com.cpu.quikdata.feature.createform.livelihoodsinfo.estimateddamage

import android.app.Application
import androidx.lifecycle.LiveData
import com.cpu.quikdata.base.BaseRepository
import com.cpu.quikdata.data.livelihoodsinfo.estimateddamage.EstimatedDamageComplete
import com.cpu.quikdata.utils.runOnIoThread

class EstimatedDamageRepository(application: Application, formId: String) :
    BaseRepository<EstimatedDamageComplete>(application) {

    private val mEstimatedDamage = mDatabase.estimatedDamageRowDao().getByFormId(formId)

    val estimatedDamage: LiveData<List<EstimatedDamageComplete>>
        get() = mEstimatedDamage

    override fun updateData(data: EstimatedDamageComplete) {
        runOnIoThread {
            mDatabase.estimatedDamageRowDao().update(data.row!!)
        }
    }
}