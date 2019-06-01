package com.cpu.quikdata.feature.createform.shelterinfo.shelterneeds

import android.app.Application
import androidx.lifecycle.LiveData
import com.cpu.quikdata.base.BaseRepository
import com.cpu.quikdata.data.shelterinfo.shelterneedsrow.ShelterNeedsRow
import com.cpu.quikdata.utils.runOnIoThread

class ShelterNeedsRepository(application: Application, formId: String) :
    BaseRepository<ShelterNeedsRow>(application) {

    private val mShelterNeeds = mDatabase.shelterNeedsRowDao().getByFormId(formId)

    val shelterNeeds: LiveData<List<ShelterNeedsRow>>
        get() = mShelterNeeds

    override fun updateData(data: ShelterNeedsRow) {
        runOnIoThread {
            mDatabase.shelterNeedsRowDao().update(data)
        }
    }
}