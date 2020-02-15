package com.cpu.quikdata.feature.createform.healthinfo.specialneeds

import androidx.lifecycle.LiveData
import com.cpu.quikdata.base.BaseUpdateableRepository
import com.cpu.quikdata.data.AppDatabase
import com.cpu.quikdata.data.health.specialneedsrow.SpecialNeedsRow
import com.cpu.quikdata.utils.runOnIoThread

class SpecialNeedsRepository(private val mDatabase: AppDatabase, val formId: String) :
    BaseUpdateableRepository<SpecialNeedsRow>() {

    private val mSpecialNeeds = mDatabase.specialNeedsRowDao().getByFormId(formId)

    val specialNeeds: LiveData<List<SpecialNeedsRow>>
        get() = mSpecialNeeds

    override fun updateData(data: SpecialNeedsRow) {
        runOnIoThread {
            mDatabase.specialNeedsRowDao().update(data)
        }
    }
}