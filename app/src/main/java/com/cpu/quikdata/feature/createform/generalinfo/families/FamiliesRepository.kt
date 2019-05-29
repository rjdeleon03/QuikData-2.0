package com.cpu.quikdata.feature.createform.generalinfo.families

import android.app.Application
import androidx.lifecycle.LiveData
import com.cpu.quikdata.data.AppDatabase
import com.cpu.quikdata.data.generalinfo.families.Families
import com.cpu.quikdata.utils.runOnIoThread

class FamiliesRepository(application: Application, formId: String) {

    private val mDatabase = AppDatabase.get(application)
    private val mFamilies = mDatabase.familiesDao().getByFormId(formId)

    val families: LiveData<Families>
        get() = mFamilies

    fun updateFamilies(families: Families) {
        runOnIoThread {
            val oldFamilies = mFamilies.value!!
            oldFamilies.copyFrom(families)
            mDatabase.familiesDao().update(oldFamilies)
        }
    }
}