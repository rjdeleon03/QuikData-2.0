package com.cpu.quikdata.feature.createform.generalinfo.casualties

import androidx.lifecycle.LiveData
import com.cpu.quikdata.data.AppDatabase
import com.cpu.quikdata.data.generalinfo.casualtiesrow.CasualtiesRow
import javax.inject.Inject

class CasualtiesRepository @Inject constructor(
    private val mDatabase: AppDatabase, formId: String
) {

    private val mCasualties = mDatabase.casualtiesRowDao().getByFormId(formId)

    val casualties: LiveData<List<CasualtiesRow>>
        get() = mCasualties

    suspend fun updateData(data: CasualtiesRow) {
        mDatabase.casualtiesRowDao().update(data)
    }
}