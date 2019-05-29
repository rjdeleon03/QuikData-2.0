package com.cpu.quikdata.feature.createform.generalinfo.vulnerable

import android.app.Application
import androidx.lifecycle.LiveData
import com.cpu.quikdata.data.AppDatabase
import com.cpu.quikdata.data.generalinfo.vulnerablerow.VulnerableRow
import com.cpu.quikdata.utils.runOnIoThread

class VulnerableRepository(application: Application, formId: String) {

    private val mDatabase = AppDatabase.get(application)
    private val mVulnerable = mDatabase.vulnerableRowDao().getByFormId(formId)

    val vulnerable: LiveData<List<VulnerableRow>>
        get() = mVulnerable

    fun updateRow(vulnerableRow: VulnerableRow) {
        runOnIoThread {
            mDatabase.vulnerableRowDao().insert(vulnerableRow)
        }
    }
}