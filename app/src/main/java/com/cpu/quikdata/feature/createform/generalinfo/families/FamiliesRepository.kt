package com.cpu.quikdata.feature.createform.generalinfo.families

import android.app.Application
import androidx.lifecycle.LiveData
import com.cpu.quikdata.base.BaseRepository
import com.cpu.quikdata.data.AppDatabase
import com.cpu.quikdata.data.generalinfo.families.Families
import com.cpu.quikdata.utils.runOnIoThread

class FamiliesRepository(application: Application, formId: String):
    BaseRepository<Families>(application) {

    private val mFamilies = mDatabase.familiesDao().getByFormId(formId)

    val families: LiveData<Families>
        get() = mFamilies

    override fun updateData(data: Families) {
        runOnIoThread {
            val oldFamilies = mFamilies.value!!
            oldFamilies.copyFrom(data)
            mDatabase.familiesDao().update(oldFamilies)
        }
    }
}