package com.cpu.quikdata.feature.createform.generalinfo.families

import androidx.lifecycle.LiveData
import com.cpu.quikdata.data.AppDatabase
import com.cpu.quikdata.data.generalinfo.families.Families
import javax.inject.Inject

class FamiliesRepository @Inject constructor(
    private val mDatabase: AppDatabase, formId: String
) {

    private val mFamilies = mDatabase.familiesDao().getByFormId(formId)

    val families: LiveData<Families>
        get() = mFamilies

    suspend fun updateData(data: Families) {
        mFamilies.value?.apply {
            copyFrom(data)
            mDatabase.familiesDao().update(this)
        }
    }
}