package com.cpu.quikdata.feature.createform.livelihoodsinfo.livelihoodsgaps

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.cpu.quikdata.common.runOnIoThread
import com.cpu.quikdata.data.livelihoodsinfo.livelihoodsgaps.LivelihoodsGaps
import javax.inject.Inject

class LivelihoodsGapsViewModel @Inject constructor (private val mRepository: LivelihoodsGapsRepository)
    : ViewModel() {

    val livelihoodsGaps : LiveData<LivelihoodsGaps>
        get() = mRepository.livelihoodsGaps

    fun updateLivelihoodsGaps(livelihoodsGaps: LivelihoodsGaps) =
        runOnIoThread { mRepository.updateData(livelihoodsGaps) }
}
