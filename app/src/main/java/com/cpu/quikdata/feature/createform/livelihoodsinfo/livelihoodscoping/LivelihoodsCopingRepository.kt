package com.cpu.quikdata.feature.createform.livelihoodsinfo.livelihoodscoping

import androidx.lifecycle.LiveData
import com.cpu.quikdata.base.BaseUpdateableRepository
import com.cpu.quikdata.data.AppDatabase
import com.cpu.quikdata.data.livelihoodsinfo.livelihoodscoping.LivelihoodsCoping

class LivelihoodsCopingRepository(private val mDatabase: AppDatabase, val formId: String) :
    BaseUpdateableRepository<LivelihoodsCoping>() {

    private val mLivelihoodsCoping = mDatabase.livelihoodsCopingDao().getByFormId(formId)

    val livelihoodsCoping : LiveData<LivelihoodsCoping>
        get() = mLivelihoodsCoping

    override suspend fun updateData(data: LivelihoodsCoping) {
        mLivelihoodsCoping.value?.let {
            it.copyFrom(data)
            mDatabase.livelihoodsCopingDao().update(it)
        }
    }
}