package com.cpu.quikdata.feature.createform.livelihoodsinfo.livelihoodsgaps

import androidx.lifecycle.LiveData
import com.cpu.quikdata.base.BaseUpdateableRepository
import com.cpu.quikdata.data.AppDatabase
import com.cpu.quikdata.data.livelihoodsinfo.livelihoodsgaps.LivelihoodsGaps

class LivelihoodsGapsRepository(private val mDatabase: AppDatabase, val formId: String) :
    BaseUpdateableRepository<LivelihoodsGaps>() {

    private val mLivelihoodsGaps = mDatabase.livelihoodsGapsDao().getByFormId(formId)

    val livelihoodsGaps : LiveData<LivelihoodsGaps>
        get() = mLivelihoodsGaps

    override suspend fun updateData(data: LivelihoodsGaps) {
        mLivelihoodsGaps.value?.let {
            it.copyFrom(data)
            mDatabase.livelihoodsGapsDao().update(it)
        }
    }
}