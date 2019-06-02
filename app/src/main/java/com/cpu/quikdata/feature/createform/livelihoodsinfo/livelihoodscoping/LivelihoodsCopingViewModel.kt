package com.cpu.quikdata.feature.createform.livelihoodsinfo.livelihoodscoping

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.cpu.quikdata.data.livelihoodsinfo.livelihoodscoping.LivelihoodsCoping

class LivelihoodsCopingViewModel(application: Application, formId: String) :
    AndroidViewModel(application) {

    private val mRepository = LivelihoodsCopingRepository(application, formId)

    val livelihoodsCoping : LiveData<LivelihoodsCoping>
        get() = mRepository.livelihoodsCoping

    fun updateLivelihoodsCoping(livelihoodsCoping: LivelihoodsCoping) =
        mRepository.updateData(livelihoodsCoping)
}
