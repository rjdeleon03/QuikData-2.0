package com.cpu.quikdata.feature.createform.livelihoodsinfo.livelihoodscoping

import android.app.Application
import androidx.lifecycle.LiveData
import com.cpu.quikdata.base.BaseRepository
import com.cpu.quikdata.data.livelihoodsinfo.livelihoodscoping.LivelihoodsCoping
import com.cpu.quikdata.utils.runOnIoThread

class LivelihoodsCopingRepository(application: Application, formId: String) :
    BaseRepository<LivelihoodsCoping>(application) {

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