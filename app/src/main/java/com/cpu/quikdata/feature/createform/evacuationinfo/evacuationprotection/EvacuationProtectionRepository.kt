package com.cpu.quikdata.feature.createform.evacuationinfo.evacuationprotection

import androidx.lifecycle.LiveData
import com.cpu.quikdata.data.AppDatabase
import com.cpu.quikdata.data.evacuation.evacuationprotection.EvacuationProtection
import com.cpu.quikdata.di.EvacuationId
import com.cpu.quikdata.utils.runOnIoThread
import javax.inject.Inject

class EvacuationProtectionRepository @Inject constructor(private val mDatabase: AppDatabase,
                                                         @EvacuationId evacuationId: String) {

    private val mEvacuationProtection = mDatabase.evacuationProtectionDao().getByEvacuationId(evacuationId)

    val evacuationProtection: LiveData<EvacuationProtection>
        get() = mEvacuationProtection

    fun updateData(data: EvacuationProtection) {
        runOnIoThread {
            val oldEvacuationProtection = mEvacuationProtection.value!!
            oldEvacuationProtection.copyFrom(data)
            mDatabase.evacuationProtectionDao().update(oldEvacuationProtection)
        }
    }
}