package com.cpu.quikdata.feature.createform.livelihoodsinfo.livelihoodsneeds

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.cpu.quikdata.data.livelihoodsinfo.livelihoodsneeds.LivelihoodsNeeds

class LivelihoodsNeedsViewModel(application: Application, formId: String) :
    AndroidViewModel(application) {

    private val mRepository = LivelihoodsNeedsRepository(application, formId)

    val livelihoodsNeeds : LiveData<LivelihoodsNeeds>
        get() = mRepository.livelihoodsNeeds

    fun updateLivelihoodsNeeds(livelihoodsNeeds: LivelihoodsNeeds) =
        mRepository.updateData(livelihoodsNeeds)
}
