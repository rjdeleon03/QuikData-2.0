package com.cpu.quikdata.feature.createform.generalinfo.vulnerable

import androidx.lifecycle.LiveData
import com.cpu.quikdata.base.BaseUpdateableRepository
import com.cpu.quikdata.data.AppDatabase
import com.cpu.quikdata.data.generalinfo.vulnerablerow.VulnerableRow

class VulnerableRepository(private val mDatabase: AppDatabase, val formId: String) :
    BaseUpdateableRepository<VulnerableRow>() {

    private val mVulnerable = mDatabase.vulnerableRowDao().getByFormId(formId)

    val vulnerable: LiveData<List<VulnerableRow>>
        get() = mVulnerable

    override suspend fun updateData(data: VulnerableRow) {
        mDatabase.vulnerableRowDao().update(data)
    }
}