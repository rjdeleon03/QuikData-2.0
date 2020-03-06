package com.cpu.quikdata.feature.createform.livelihoodsinfo.livelihoodscoping

import androidx.lifecycle.LiveData
import com.cpu.quikdata.data.AppDatabase
import com.cpu.quikdata.data.livelihoodsinfo.livelihoodscoping.LivelihoodsCoping
import com.cpu.quikdata.utils.runOnIoThread
import javax.inject.Inject

class LivelihoodsCopingRepository @Inject constructor(private val mDatabase: AppDatabase, formId: String) {

    private val mLivelihoodsCoping = mDatabase.livelihoodsCopingDao().getByFormId(formId)

    val livelihoodsCoping : LiveData<LivelihoodsCoping>
        get() = mLivelihoodsCoping

    fun updateData(data: LivelihoodsCoping) {
        runOnIoThread {
            val oldCoping = mLivelihoodsCoping.value!!
            oldCoping.copyFrom(data)
            mDatabase.livelihoodsCopingDao().update(oldCoping)
        }
    }
}