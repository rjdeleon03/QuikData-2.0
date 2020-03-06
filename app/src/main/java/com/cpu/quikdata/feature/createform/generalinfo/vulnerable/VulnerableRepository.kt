package com.cpu.quikdata.feature.createform.generalinfo.vulnerable

import androidx.lifecycle.LiveData
import com.cpu.quikdata.data.AppDatabase
import com.cpu.quikdata.data.generalinfo.vulnerablerow.VulnerableRow
import com.cpu.quikdata.utils.runOnIoThread
import javax.inject.Inject

class VulnerableRepository @Inject constructor(
    private val mDatabase: AppDatabase, formId: String) {

    private val mVulnerable = mDatabase.vulnerableRowDao().getByFormId(formId)

    val vulnerable: LiveData<List<VulnerableRow>>
        get() = mVulnerable

    fun updateData(data: VulnerableRow) {
        runOnIoThread {
            mDatabase.vulnerableRowDao().update(data)
        }
    }
}