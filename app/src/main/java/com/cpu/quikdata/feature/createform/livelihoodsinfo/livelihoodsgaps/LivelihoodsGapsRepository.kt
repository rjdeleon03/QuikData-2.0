package com.cpu.quikdata.feature.createform.livelihoodsinfo.livelihoodsgaps

import androidx.lifecycle.LiveData
import com.cpu.quikdata.data.AppDatabase
import com.cpu.quikdata.data.livelihoodsinfo.livelihoodsgaps.LivelihoodsGaps
import com.cpu.quikdata.utils.runOnIoThread
import javax.inject.Inject

class LivelihoodsGapsRepository @Inject constructor(private val mDatabase: AppDatabase, formId: String) {

    private val mLivelihoodsGaps = mDatabase.livelihoodsGapsDao().getByFormId(formId)

    val livelihoodsGaps : LiveData<LivelihoodsGaps>
        get() = mLivelihoodsGaps

    fun updateData(data: LivelihoodsGaps) {
        runOnIoThread {
            val oldGaps = mLivelihoodsGaps.value!!
            oldGaps.copyFrom(data)
            mDatabase.livelihoodsGapsDao().update(oldGaps)
        }
    }
}