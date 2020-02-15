package com.cpu.quikdata.feature.createform.generalinfo.families

import androidx.lifecycle.LiveData
import com.cpu.quikdata.base.BaseUpdateableRepository
import com.cpu.quikdata.data.AppDatabase
import com.cpu.quikdata.data.generalinfo.families.Families

class FamiliesRepository(private val mDatabase: AppDatabase, val formId: String) :
    BaseUpdateableRepository<Families>() {

    private val mFamilies = mDatabase.familiesDao().getByFormId(formId)

    val families: LiveData<Families>
        get() = mFamilies

    override suspend fun updateData(data: Families) {
        mFamilies.value?.let {
            it.copyFrom(data)
            mDatabase.familiesDao().update(it)
        }
    }
}