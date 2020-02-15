package com.cpu.quikdata.feature.createform.generalinfo.vulnerable

import androidx.lifecycle.LiveData
import com.cpu.quikdata.base.BaseUpdateableRepository
import com.cpu.quikdata.data.AppDatabase
import com.cpu.quikdata.data.generalinfo.vulnerablerow.VulnerableRow
import com.cpu.quikdata.utils.runOnIoThread

class VulnerableRepository(private val mDatabase: AppDatabase, val formId: String) :
    BaseUpdateableRepository<VulnerableRow>() {

    private val mVulnerable = mDatabase.vulnerableRowDao().getByFormId(formId)

    val vulnerable: LiveData<List<VulnerableRow>>
        get() = mVulnerable

    override fun updateData(data: VulnerableRow) {
        runOnIoThread {
            mDatabase.vulnerableRowDao().update(data)
        }
    }
}