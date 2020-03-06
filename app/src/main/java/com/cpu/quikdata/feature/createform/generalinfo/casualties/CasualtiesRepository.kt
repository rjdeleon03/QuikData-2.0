package com.cpu.quikdata.feature.createform.generalinfo.casualties

import androidx.lifecycle.LiveData
import com.cpu.quikdata.data.AppDatabase
import com.cpu.quikdata.data.generalinfo.casualtiesrow.CasualtiesRow
import com.cpu.quikdata.utils.runOnIoThread
import javax.inject.Inject

class CasualtiesRepository @Inject constructor(
    private val mDatabase: AppDatabase, private val mFormId: String) {

    private val mCasualties = mDatabase.casualtiesRowDao().getByFormId(mFormId)

    val casualties: LiveData<List<CasualtiesRow>>
        get() = mCasualties

    fun updateData(data: CasualtiesRow) {
        runOnIoThread {
            mDatabase.casualtiesRowDao().update(data)
        }
    }
}