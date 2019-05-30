package com.cpu.quikdata.feature.createform.generalinfo.vulnerable

import android.app.Application
import androidx.lifecycle.LiveData
import com.cpu.quikdata.base.BaseRepository
import com.cpu.quikdata.data.AppDatabase
import com.cpu.quikdata.data.generalinfo.vulnerablerow.VulnerableRow
import com.cpu.quikdata.utils.runOnIoThread

class VulnerableRepository(application: Application, formId: String) :
    BaseRepository<VulnerableRow>(application) {

    private val mVulnerable = mDatabase.vulnerableRowDao().getByFormId(formId)

    val vulnerable: LiveData<List<VulnerableRow>>
        get() = mVulnerable

    override fun updateData(data: VulnerableRow) {
        runOnIoThread {
            mDatabase.vulnerableRowDao().update(data)
        }
    }
}