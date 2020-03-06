package com.cpu.quikdata.feature.createform.livelihoodsinfo.livelihoodscoping

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.cpu.quikdata.data.livelihoodsinfo.livelihoodscoping.LivelihoodsCoping
import javax.inject.Inject

class LivelihoodsCopingViewModel @Inject constructor(private val mRepository: LivelihoodsCopingRepository)
    : ViewModel() {

    val livelihoodsCoping : LiveData<LivelihoodsCoping>
        get() = mRepository.livelihoodsCoping

    fun updateLivelihoodsCoping(livelihoodsCoping: LivelihoodsCoping) =
        mRepository.updateData(livelihoodsCoping)
}
