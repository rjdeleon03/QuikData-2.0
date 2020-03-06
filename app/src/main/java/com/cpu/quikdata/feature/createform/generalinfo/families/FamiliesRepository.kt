package com.cpu.quikdata.feature.createform.generalinfo.families

import androidx.lifecycle.LiveData
import com.cpu.quikdata.data.AppDatabase
import com.cpu.quikdata.data.generalinfo.families.Families
import com.cpu.quikdata.utils.runOnIoThread
import javax.inject.Inject

class FamiliesRepository @Inject constructor(
    private val mDatabase: AppDatabase, private val mFormId: String) {

    private val mFamilies = mDatabase.familiesDao().getByFormId(mFormId)

    val families: LiveData<Families>
        get() = mFamilies

    fun updateData(data: Families) {
        runOnIoThread {
            val oldFamilies = mFamilies.value!!
            oldFamilies.copyFrom(data)
            mDatabase.familiesDao().update(oldFamilies)
        }
    }
}