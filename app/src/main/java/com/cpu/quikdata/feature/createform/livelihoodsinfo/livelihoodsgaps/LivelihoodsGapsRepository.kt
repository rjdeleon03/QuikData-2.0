package com.cpu.quikdata.feature.createform.livelihoodsinfo.livelihoodsgaps

import android.app.Application
import androidx.lifecycle.LiveData
import com.cpu.quikdata.base.BaseRepository
import com.cpu.quikdata.data.livelihoodsinfo.livelihoodsgaps.LivelihoodsGaps
import com.cpu.quikdata.utils.runOnIoThread

class LivelihoodsGapsRepository(application: Application, formId: String) :
    BaseRepository<LivelihoodsGaps>(application) {

    private val mLivelihoodsGaps = mDatabase.livelihoodsGapsDao().getByFormId(formId)

    val livelihoodsGaps : LiveData<LivelihoodsGaps>
        get() = mLivelihoodsGaps

    override fun updateData(data: LivelihoodsGaps) {
        runOnIoThread {
            val oldGaps = mLivelihoodsGaps.value!!
            oldGaps.copyFrom(data)
            mDatabase.livelihoodsGapsDao().update(oldGaps)
        }
    }
}