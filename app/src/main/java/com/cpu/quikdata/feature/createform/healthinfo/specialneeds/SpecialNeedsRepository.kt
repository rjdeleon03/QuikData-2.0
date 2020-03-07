package com.cpu.quikdata.feature.createform.healthinfo.specialneeds

import androidx.lifecycle.LiveData
import com.cpu.quikdata.data.AppDatabase
import com.cpu.quikdata.data.health.specialneedsrow.SpecialNeedsRow
import javax.inject.Inject

class SpecialNeedsRepository @Inject constructor(
    private val mDatabase: AppDatabase,
    formId: String
) {

    private val mSpecialNeeds = mDatabase.specialNeedsRowDao().getByFormId(formId)

    val specialNeeds: LiveData<List<SpecialNeedsRow>>
        get() = mSpecialNeeds

    suspend fun updateData(data: SpecialNeedsRow) {
        mDatabase.specialNeedsRowDao().update(data)
    }
}