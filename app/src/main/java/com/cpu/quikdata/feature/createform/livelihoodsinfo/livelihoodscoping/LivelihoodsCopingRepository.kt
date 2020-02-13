package com.cpu.quikdata.feature.createform.livelihoodsinfo.livelihoodscoping

import androidx.lifecycle.LiveData
import com.cpu.quikdata.base.BaseUpdateableRepository
import com.cpu.quikdata.data.AppDatabase
import com.cpu.quikdata.data.livelihoodsinfo.livelihoodscoping.LivelihoodsCoping
import com.cpu.quikdata.utils.runOnIoThread

class LivelihoodsCopingRepository(private val mDatabase: AppDatabase, val formId: String) :
    BaseUpdateableRepository<LivelihoodsCoping>() {

    private val mLivelihoodsCoping = mDatabase.livelihoodsCopingDao().getByFormId(formId)

    val livelihoodsCoping : LiveData<LivelihoodsCoping>
        get() = mLivelihoodsCoping

    override fun updateData(data: LivelihoodsCoping) {
        runOnIoThread {
            val oldCoping = mLivelihoodsCoping.value!!
            oldCoping.copyFrom(data)
            mDatabase.livelihoodsCopingDao().update(oldCoping)
        }
    }
}