package com.cpu.quikdata.feature.createform.healthinfo.specialneeds

import android.app.Application
import androidx.lifecycle.LiveData
import com.cpu.quikdata.base.BaseRepository
import com.cpu.quikdata.data.health.specialneedsrow.SpecialNeedsRow
import com.cpu.quikdata.utils.runOnIoThread

class SpecialNeedsRepository(application: Application, formId: String) :
    BaseRepository<SpecialNeedsRow>(application) {

    private val mSpecialNeeds = mDatabase.specialNeedsRowDao().getByFormId(formId)

    val specialNeeds: LiveData<List<SpecialNeedsRow>>
        get() = mSpecialNeeds

    override fun updateData(data: SpecialNeedsRow) {
        runOnIoThread {
            mDatabase.specialNeedsRowDao().update(data)
        }
    }
}