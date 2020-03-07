package com.cpu.quikdata.feature.createform.livelihoodsinfo.livelihoodsgaps

import androidx.lifecycle.LiveData
import com.cpu.quikdata.data.AppDatabase
import com.cpu.quikdata.data.livelihoodsinfo.livelihoodsgaps.LivelihoodsGaps
import javax.inject.Inject

class LivelihoodsGapsRepository @Inject constructor(
    private val mDatabase: AppDatabase,
    formId: String
) {

    private val mLivelihoodsGaps = mDatabase.livelihoodsGapsDao().getByFormId(formId)

    val livelihoodsGaps: LiveData<LivelihoodsGaps>
        get() = mLivelihoodsGaps

    suspend fun updateData(data: LivelihoodsGaps) {
        mLivelihoodsGaps.value?.apply {
            copyFrom(data)
            mDatabase.livelihoodsGapsDao().update(this)
        }
    }
}