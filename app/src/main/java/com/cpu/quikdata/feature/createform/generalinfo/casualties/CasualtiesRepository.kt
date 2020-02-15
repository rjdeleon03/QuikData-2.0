package com.cpu.quikdata.feature.createform.generalinfo.casualties

import androidx.lifecycle.LiveData
import com.cpu.quikdata.base.BaseUpdateableRepository
import com.cpu.quikdata.data.AppDatabase
import com.cpu.quikdata.data.generalinfo.casualtiesrow.CasualtiesRow
import com.cpu.quikdata.utils.runOnIoThread

class CasualtiesRepository(private val mDatabase: AppDatabase, val formId: String) :
    BaseUpdateableRepository<CasualtiesRow>() {

    private val mCasualties = mDatabase.casualtiesRowDao().getByFormId(formId)

    val casualties: LiveData<List<CasualtiesRow>>
        get() = mCasualties

    override fun updateData(data: CasualtiesRow) {
        runOnIoThread {
            mDatabase.casualtiesRowDao().update(data)
        }
    }
}