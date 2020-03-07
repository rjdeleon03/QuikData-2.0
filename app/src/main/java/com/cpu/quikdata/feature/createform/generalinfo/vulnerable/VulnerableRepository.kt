package com.cpu.quikdata.feature.createform.generalinfo.vulnerable

import androidx.lifecycle.LiveData
import com.cpu.quikdata.data.AppDatabase
import com.cpu.quikdata.data.generalinfo.vulnerablerow.VulnerableRow
import javax.inject.Inject

class VulnerableRepository @Inject constructor(
    private val mDatabase: AppDatabase, formId: String
) {

    private val mVulnerable = mDatabase.vulnerableRowDao().getByFormId(formId)

    val vulnerable: LiveData<List<VulnerableRow>>
        get() = mVulnerable

    suspend fun updateData(data: VulnerableRow) {
        mDatabase.vulnerableRowDao().update(data)
    }
}