package com.cpu.quikdata.feature.createform.evacuationinfo.evacuationprotection

import android.app.Application
import androidx.lifecycle.LiveData
import com.cpu.quikdata.base.BaseRepository
import com.cpu.quikdata.data.evacuation.evacuationprotection.EvacuationProtection
import com.cpu.quikdata.utils.runOnIoThread

class EvacuationProtectionRepository(application: Application, formId: String) :
    BaseRepository<EvacuationProtection>(application) {

    private val mEvacuationProtection = mDatabase.evacuationProtectionDao().getByEvacuationId(formId)

    val evacuationProtection: LiveData<EvacuationProtection>
        get() = mEvacuationProtection

    override fun updateData(data: EvacuationProtection) {
        runOnIoThread {
            val oldEvacuationProtection = mEvacuationProtection.value!!
            oldEvacuationProtection.copyFrom(data)
            mDatabase.evacuationProtectionDao().update(oldEvacuationProtection)
        }
    }
}