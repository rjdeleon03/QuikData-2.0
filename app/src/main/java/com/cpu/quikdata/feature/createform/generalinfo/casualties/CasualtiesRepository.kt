package com.cpu.quikdata.feature.createform.generalinfo.casualties

import android.app.Application
import androidx.lifecycle.LiveData
import com.cpu.quikdata.base.BaseRepository
import com.cpu.quikdata.data.generalinfo.casualtiesrow.CasualtiesRow
import com.cpu.quikdata.utils.runOnIoThread

class CasualtiesRepository(application: Application, formId: String) :
    BaseRepository<CasualtiesRow>(application) {

    private val mCasualties = mDatabase.casualtiesRowDao().getByFormId(formId)

    val casualties: LiveData<List<CasualtiesRow>>
        get() = mCasualties

    override fun updateData(data: CasualtiesRow) {
        runOnIoThread {
            mDatabase.casualtiesRowDao().update(data)
        }
    }
}