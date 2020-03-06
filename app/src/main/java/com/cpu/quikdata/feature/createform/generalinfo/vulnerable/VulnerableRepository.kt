package com.cpu.quikdata.feature.createform.generalinfo.vulnerable

import androidx.lifecycle.LiveData
import com.cpu.quikdata.data.AppDatabase
import com.cpu.quikdata.data.generalinfo.vulnerablerow.VulnerableRow
import com.cpu.quikdata.utils.runOnIoThread
import javax.inject.Inject

class VulnerableRepository @Inject constructor(
    private val mDatabase: AppDatabase, private val mFormId: String) {

    private val mVulnerable = mDatabase.vulnerableRowDao().getByFormId(mFormId)

    val vulnerable: LiveData<List<VulnerableRow>>
        get() = mVulnerable

    fun updateData(data: VulnerableRow) {
        runOnIoThread {
            mDatabase.vulnerableRowDao().update(data)
        }
    }
}