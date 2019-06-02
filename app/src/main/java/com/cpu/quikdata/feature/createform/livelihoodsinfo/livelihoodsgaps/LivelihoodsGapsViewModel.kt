package com.cpu.quikdata.feature.createform.livelihoodsinfo.livelihoodsgaps

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.cpu.quikdata.data.livelihoodsinfo.livelihoodsgaps.LivelihoodsGaps

class LivelihoodsGapsViewModel(application: Application, formId: String) :
    AndroidViewModel(application) {

    private val mRepository = LivelihoodsGapsRepository(application, formId)

    val livelihoodsGaps : LiveData<LivelihoodsGaps>
        get() = mRepository.livelihoodsGaps

    fun updateLivelihoodsGaps(livelihoodsGaps: LivelihoodsGaps) =
        mRepository.updateData(livelihoodsGaps)
}
